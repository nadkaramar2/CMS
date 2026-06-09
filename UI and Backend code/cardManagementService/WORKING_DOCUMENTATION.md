# Card Management System — Working Features Documentation

**Project:** `cardManagementService` (TranEco)  
**Document type:** Exactly what works + requested format (Sections 1–18)  
**Base URL:** `http://localhost:8045/CardManagementAPI`

---

## Legend used in this document

| Symbol | Meaning |
|--------|---------|
| ✅ **WORKING** | Code is wired end-to-end; works when MySQL + config exist |
| ⚠️ **PARTIAL** | API runs but logic is incomplete or needs extra hardware/config |
| ❌ **NOT WORKING** | Disabled, stub, or not connected to REST |

---

========================
## 1. PROJECT OVERVIEW
========================

### Purpose (✅ WORKING system)
Card Management System (CMS) is a **banking card lifecycle backend** used to:
- Add customers and maintain contact data
- Issue debit cards (new, instant, replacement, renewal)
- Activate/block cards and manage PIN settings
- Link bank accounts to cards
- Search customers and cards
- Update spending limits
- Generate embossing files for plastic cards
- Schedule card/mobile tokens

### Business problem solved
Banks need one API layer between **Core Banking (CBS)** and **card operations** (issuance, status, limits, embossing).

### Main objectives (what actually works today)

| Objective | Status | API |
|-----------|--------|-----|
| Customer onboarding | ✅ | `customerAddition` |
| Card issuance | ✅ | `debitCardIssuance` |
| Card activation/status | ✅ | `cardStatus` |
| Account linking | ✅ | `accountManagement` |
| Card inquiry | ✅ | `searchClientCard`, `detailedClientCard`, `searchCardDetails` |
| Limit management | ✅ | `limitUpdate` |
| PIN settings | ✅ | `pinManagement` |
| Emboss file generation | ✅ | `embossRequest` |
| Real-time card auth (switch) | ❌ | `cardAuthentication` (stub only) |
| HSM crypto | ⚠️ | Only if Thales HSM is connected |

### Type of users
- **Bank CMS operators** — issue cards, change status, inquiry
- **CBS / integration layer** — calls REST APIs (JSON/XML)
- **Embossing vendor** — receives emboss files
- **Admin** — `refreshCache` after config DB changes

### Real-world working use case
```
Bank operator registers customer
  → Issues NEWCARD
  → Activates card (cardStatus)
  → Links account (accountManagement)
  → Sends emboss job (embossRequest)
  → Customer uses card (separate switch; CMS auth API is NOT real today)
```

### Banking / card lifecycle?
**Yes.** This is a **financial card management** system (issuance → status → limits → inquiry → emboss).

---

========================
## 2. TECHNOLOGY STACK
========================

| Component | Value | Working? |
|-----------|-------|----------|
| Spring Boot | 2.7.5 | ✅ |
| Java | 1.8 | ✅ |
| Build | Maven (Eclipse m2e) | ✅ in Eclipse |
| Packaging | WAR → Tomcat | ✅ |
| Database | MySQL `cardmanagement` | ✅ required |
| ORM | **None** — Spring JDBC only | ✅ |
| Cache | EhCache (`cfg_*` tables) | ✅ |
| Security | Custom AES for PAN only | ✅ partial |
| Spring Security / JWT | Not present | ❌ |
| REST | Spring `@RestController` | ✅ |
| Swagger | In POM, disabled in code | ❌ |
| Logging | Log4j 1.2 | ✅ |
| HSM | Thales TCP socket | ⚠️ optional |

### Exception handling (how it works)
- Each controller: `try/catch` → `outResponseCode = "99"`, `message = "Internal Error"`
- No `@ControllerAdvice`
- Business codes: `00` = success, `01`–`15` = CMS errors in `Constant.java`

---

========================
## 3. COMPLETE PROJECT STRUCTURE
========================

### Working packages only (data flow)

```
REST Client
    ↓
@RestController          ← HTTP in/out
    ↓
@Service (*ServiceImpl)  ← validation + EhCache lookup
    ↓
@Repository (*DaoImpl)  ← JDBC / Stored Procedures
    ↓
MySQL cardmanagement
```

| Package | Responsibility | Working modules |
|---------|----------------|-----------------|
| `customerManagement` | Customer CRUD via SP | ✅ All 5 APIs |
| `debitCardManagement` | Card lifecycle | ✅ Issuance, status, PIN, NCMC, tokens |
| `accountManagement` | Account–card link | ✅ |
| `inquiryServices` | Search/read | ✅ All 3 APIs |
| `limitUpdate` | Limits | ✅ |
| `EmbossingProcess` | File generation | ✅ |
| `scheduler` | Token cron jobs | ✅ |
| `services` + `dao` | EhCache config load | ✅ `refreshCache` |
| `utils` | PAN mask/encrypt/token | ✅ Used by issuance |
| `HSMThales` | HSM commands | ⚠️ needs HSM |
| `cardAuthentication` | Switch auth | ❌ REST stub only |
| `transaction` + `handler` | ISO8583 internal | ❌ not exposed as working REST |

---

========================
## 4. MODULE-WISE EXPLANATION (ONLY WORKING)
========================

### Module 1: Customer Management ✅ WORKING

| Item | Detail |
|------|--------|
| **Purpose** | Create/update customer profile |
| **APIs** | See table below |
| **Tables** | `customer_details`, `customer_address`, `customer_contact`, `customer_email`, `customer_document` |
| **Flow** | `CustomerAddition` → `*ServiceImpl` → validates cfg types from cache → `*DaoImpl` → **MySQL Stored Procedure** |
| **Validation** | Address/email/document type must exist in EhCache for participant |
| **Security** | No API login; PAN not involved here |
| **Transaction** | DB stored procedures handle commits |
| **Error codes** | `03`–`08`, `10`, `99` |

| API | SP Name | Status |
|-----|---------|--------|
| `POST /customerAddition` | `customerAddition` | ✅ |
| `POST /addressMaintainance` | `addressMaintainance` | ✅ |
| `POST /phoneMaintainance` | `phoneMaintainance` | ✅ |
| `POST /emailMaintainance` | `emailMaintainance` | ✅ |
| `POST /documentMaintainance` | `documentMaintainance` | ✅ |
| `GET /refreshCache` | Reload `cfg_*` | ✅ |

**Example scenario:** New customer with CIF `CIF001` → response `outcustomerid: "1001"`, `outResponseCode: "00"`.

---

### Module 2: Debit Card Issuance ✅ WORKING

| Item | Detail |
|------|--------|
| **Purpose** | Create and manage card records |
| **API** | `POST /debitCardIssuance` |
| **Tables** | `card_details`, `card_plastic_details` |
| **Functions** | `NEWCARD`, `INSTANTCARD`, `RPLCARD`, `RNWCARD` |

| Function | What happens | Status |
|----------|--------------|--------|
| `NEWCARD` | Generate/sequence PAN → encrypt → insert card + plastic | ✅ |
| `INSTANTCARD` | Create minimal customer + card in one call | ✅ |
| `RPLCARD` | Hotlist old (optional) → new PAN + plastic | ✅ |
| `RNWCARD` | New member number, same customer, new expiry | ✅ |

**Validation rules (working):**
- `inPartID + inCardType` must exist in `cfg_card_type` (cache)
- BIN must not be null
- `NEWCARD`: `inCard` must be empty
- `RPLCARD`/`RNWCARD`: `inCard` must be provided

**Sample request (NEWCARD):**
```json
{
  "inPartID": "1",
  "inFunction": "NEWCARD",
  "inCustomerID": "1001",
  "inCardType": "01",
  "inEmbossLine1": "JOHN DOE",
  "inUserID": "admin",
  "inInstantFlag": "N",
  "inBranchCode": "001"
}
```

**Sample response:**
```json
{
  "outResponseCode": "00",
  "message": "Success",
  "outCard": "4111-XXXX-XXXX-1234",
  "outTokenCard": "TKN20240521123456"
}
```

---

### Module 3: Card Status (Activation / Block) ✅ WORKING

| Item | Detail |
|------|--------|
| **Purpose** | Change card status and write audit history |
| **API** | `POST /cardStatus` |
| **Tables** | `card_details`, `card_status_history`, `cfg_status` |

**Working flow:**
```
cardStatus API
  → DebitCardIssuanceServiceImpl.cardStatusRequest()
  → If status code exists in cfg_status (cache)
  → DebitCardIssuanceDaoImpl.getCardDetailsBasedOnCardNumber()
  → UPDATE card_details SET card_status = ?
  → INSERT card_status_history
  → Return 00
```

**Important:** Use `strCardNo` = **token_card** (internal ID), not clear PAN.

**Sample (activation):**
```json
{
  "strParticipant_ID": "1",
  "strCardNo": "TKN20240521123456",
  "strMemberNo": "1",
  "strStatusCode": "1",
  "strDescription": "Card activated",
  "strStatusChangeUser": "admin"
}
```

| Response code | Meaning |
|---------------|---------|
| `00` | Success |
| `91` | Invalid/permanent status |
| `93` | Card not found |
| `99` | Invalid status code (not in cfg_status) |

---

### Module 4: PIN Management ✅ WORKING

| API | `POST /pinManagement` |
| **Updates** | PIN mailer flag, daily/consecutive PIN retry limits |
| **Tables** | `card_plastic_details`, `card_details` |

**Validation:** At least one of `strPinMailerUpdateFlag` or `strPinRetryFlag` must be `"Y"`.

⚠️ **Known bug:** On exception, controller may throw NPE (`response` starts as `null`). Normal success path works.

---

### Module 5: Account Management ✅ WORKING

| API | `POST /accountManagement` |
| **SP** | `{call accountManagement(...)}` |
| **Functions** | Link account, delink, primary update (via `strFunctionType`) |
| **Table** | `card_account_linkage` |
| **Validation** | Account type must exist in `cfg_account_type` cache |

---

### Module 6: Inquiry Services ✅ WORKING

| API | Purpose | Status |
|-----|---------|--------|
| `POST /searchClientCard` | Search by name, card, account, CIF, citizen, type | ✅ |
| `POST /detailedClientCard` | Full profile: customer, cards, accounts, address, phone, email, docs | ✅ |
| `POST /searchCardDetails` | Card detail, limits, status history, NCMC, accounts | ✅ |

---

### Module 7: Limit Update ✅ WORKING

| API | `POST /limitUpdate` |
| **SP** | `limitUpdate` |
| **Updates** | ATM, POS, ECOM, offline, monthly, weekly, daily limits |

---

### Module 8: NCMC Service ✅ WORKING

| API | `POST /NCMCServiceUpdation` |
| **Logic** | Delete existing services → insert new list |
| **Table** | `card_ncmc_service` |

---

### Module 9: Token Scheduler ✅ WORKING

| API | Purpose |
|-----|---------|
| `POST /scheduleCardToken` | Start cron/on-demand card token job |
| `POST /getCardToken` | Read latest token for encrypted card |
| `POST /scheduleMobileToken` | Schedule mobile token |
| `POST /getMobileToken` | Get mobile token list |

**Needs:** `cfg_card_token` / `cfg_mobile_token` rows in DB.

---

### Module 10: Embossing ✅ WORKING (with file paths)

| API | `POST /embossRequest` |
| **Input** | `{ "cardType": "participantId|cardTypeId" }` |
| **Output** | Emboss file on disk |
| **DB** | Sets `emboss_flag = 'Y'` on processed cards |

**Needs:**
- `emboss.file.path` in `application.properties`
- `emboss.xml` at configured path
- Cards with `emboss_flag IS NULL`

---

### Module 11: HSM Thales ⚠️ PARTIAL

| APIs | Status |
|------|--------|
| `generateCVV`, `validateCVV`, `validatePinBlock`, `generateRandomPin`, etc. | Works **only if** HSM at `hsmip:hsmport` is running |

---

### Module 12: Card Authentication ❌ NOT WORKING (real logic)

| API | `POST /cardAuthentication` |
| **What happens** | Returns fake `responseCode: "00"` by processing code — **no DB/HSM validation** |
| **Real logic exists in** | `CardAuthenticationDaoImpl` — **not called** by controller |

---

========================
## 5. APPLICATION WORKFLOW (EXACTLY WORKING PATH)
========================

### Step-by-step — production flow that works today

```
STEP 1: Load configuration
  GET /refreshCache
  → EhCacheServiceImpl.refreshCache()
  → Loads cfg_participant, cfg_card_type, cfg_status, etc.

STEP 2: Register customer
  POST /customerAddition
  → SP customerAddition
  → Returns outcustomerid

STEP 3: Issue card
  POST /debitCardIssuance  (inFunction: NEWCARD)
  → Validate card type from cache
  → Generate PAN, encrypt, mask
  → INSERT card_details + card_plastic_details
  → Returns outTokenCard (save this!)

STEP 4: Activate card
  POST /cardStatus  (strCardNo = outTokenCard)
  → UPDATE card_status + INSERT card_status_history

STEP 5: Link account
  POST /accountManagement
  → SP accountManagement

STEP 6: Emboss plastic
  POST /embossRequest
  → File written to emboss.file.path

STEP 7: Inquiry (any time)
  POST /searchClientCard OR /detailedClientCard OR /searchCardDetails

OPTIONAL:
  POST /limitUpdate
  POST /pinManagement
  POST /NCMCServiceUpdation
  POST /scheduleCardToken
```

### State transitions (working)

| Action | API | DB change |
|--------|-----|-----------|
| Issue | `debitCardIssuance` | New row in `card_details` status from template |
| Activate | `cardStatus` | `card_status` updated + history row |
| Block | `cardStatus` | Same with block status code from `cfg_status` |
| Replace | `debitCardIssuance` RPLCARD | New card row; optional hotlist status 11 |

---

========================
## 6. REQUEST FLOW ARCHITECTURE (WORKING LAYERS)
========================

```
Client (Postman / CBS)
    │
    ▼
┌─────────────────────────────────────────────────────────┐
│ CONTROLLER  e.g. DebitCardIssuance.debitCardIssuanceRequest│
│ • Receives JSON/XML                                      │
│ • try/catch → 99 on error                                │
└──────────────────────────┬──────────────────────────────┘
                           ▼
┌─────────────────────────────────────────────────────────┐
│ SERVICE  e.g. DebitCardIssuanceServiceImpl               │
│ • Cache validation (card type, BIN, function)              │
│ • CardNumberGenerator / business rules                   │
└──────────────────────────┬──────────────────────────────┘
                           ▼
┌─────────────────────────────────────────────────────────┐
│ DAO  e.g. DebitCardIssuanceDaoImpl                       │
│ • JdbcTemplate.update(INSERT...)                         │
│ • OR SimpleJdbcCall (stored procedures)                  │
└──────────────────────────┬──────────────────────────────┘
                           ▼
                      MySQL Database
```

| Layer | Working responsibility |
|-------|------------------------|
| Controller | HTTP mapping only |
| Service | Business validation + EhCache |
| DAO | SQL execution |
| EhCache | Fast cfg lookup (required before issuance) |

---

========================
## 7. DATABASE DESIGN (TABLES USED BY WORKING APIs)
========================

### Core working tables

| Table | Used by | Purpose |
|-------|---------|---------|
| `customer_details` | customerAddition | Customer master |
| `card_details` | debitCardIssuance, cardStatus, inquiry | Card master (enc_card, token_card, limits, status) |
| `card_plastic_details` | issuance, emboss | Emboss lines, mailer flags |
| `card_account_linkage` | accountManagement | Card–account mapping |
| `card_status_history` | cardStatus | Audit trail |
| `card_limit_statistics` | searchCardDetails | Used limit counters |
| `card_ncmc_service` | NCMCServiceUpdation | NCMC flags |
| `card_token` | getCardToken, scheduler | Tokenization JSON |
| `cfg_*` | EhCache / validation | All configuration |

### Relationships (working flow)

```
customer_details (1) ──→ (N) card_details
card_details (1) ──→ (N) card_plastic_details  [member_number]
card_details (1) ──→ (N) card_account_linkage
card_details (1) ──→ (N) card_status_history
```

### Keys used in APIs
- **token_card** — internal card ID (use in `cardStatus`, account link)
- **enc_card** — AES encrypted PAN (used in search)
- **card_number** — masked PAN shown to users

---

========================
## 8. SECURITY FLOW (WHAT ACTUALLY WORKS)
========================

### Authentication / authorization
| Feature | Status |
|---------|--------|
| API login (JWT/OAuth) | ❌ Not implemented — APIs are open |
| Role-based access | ❌ Not implemented |

### What works for card data security
```
Clear PAN (in memory during issuance)
    → CardUtils.encrypt()  [AES]
    → Stored in card_details.enc_card
    → API returns only masked PAN (####-XXXX-XXXX-####)
    → Operations use token_card internally
```

**Key files required:**
- `card_encrypt_key` table (DB)
- `key.txt` file (path in `CardUtils.java`)

### HSM (optional, working if connected)
```
POST /generateCVV → HSMThalesServiceImpl → TCP socket → Thales HSM
```

---

========================
## 9. API DOCUMENTATION (WORKING ENDPOINTS ONLY)
========================

**Base:** `http://localhost:8045/CardManagementAPI`  
**Headers:** `Content-Type: application/json` (or `text/xml`)

### ✅ Working APIs summary table

| # | Method | Endpoint | Success code |
|---|--------|----------|--------------|
| 1 | GET | `/refreshCache` | SUCCESS text |
| 2 | POST | `/customerAddition` | `00` |
| 3 | POST | `/addressMaintainance` | `00` |
| 4 | POST | `/phoneMaintainance` | `00` |
| 5 | POST | `/emailMaintainance` | `00` |
| 6 | POST | `/documentMaintainance` | `00` |
| 7 | POST | `/debitCardIssuance` | `00` |
| 8 | POST | `/cardStatus` | `00` |
| 9 | POST | `/pinManagement` | `00` |
| 10 | POST | `/accountManagement` | `00` |
| 11 | POST | `/searchClientCard` | `00` |
| 12 | POST | `/detailedClientCard` | `00` |
| 13 | POST | `/searchCardDetails` | `00` |
| 14 | POST | `/limitUpdate` | `00` |
| 15 | POST | `/NCMCServiceUpdation` | `00` |
| 16 | POST | `/embossRequest` | void (no body) |
| 17 | POST | `/scheduleCardToken` | `00` |
| 18 | POST | `/getCardToken` | `00` |
| 19 | POST | `/scheduleMobileToken` | `00` |
| 20 | POST | `/getMobileToken` | `00` |

### ❌ Not working as real features

| Endpoint | Issue |
|----------|-------|
| `/cardAuthentication` | Stub approvals only |
| Swagger UI | Disabled |
| ISO8583 switch handler | Not exposed via REST |

---

### Detailed working API: `debitCardIssuance`

| Field | Value |
|-------|-------|
| **URL** | `POST /CardManagementAPI/debitCardIssuance` |
| **Class** | `DebitCardIssuance.debitCardIssuanceRequest()` |
| **Service** | `DebitCardIssuanceServiceImpl.debitCardIssuanceRequest()` |
| **DAO** | `DebitCardIssuanceDaoImpl.debitCardIssuanceRequest()` |

**Request body:**
```json
{
  "inPartID": "1",
  "inFunction": "NEWCARD",
  "inCustomerID": "1001",
  "inCardType": "01",
  "inCardIssueCode": "P",
  "inEmbossLine1": "JOHN DOE",
  "inEmbossLine2": "",
  "inEncodeFirstName": "JOHN",
  "inEncodeLastName": "DOE",
  "inUserID": "admin",
  "inInstantFlag": "N",
  "inBranchCode": "001"
}
```

**Response:**
```json
{
  "outResponseCode": "00",
  "message": "Success",
  "outCard": "4111-XXXX-XXXX-5678",
  "outTokenCard": "TKN9876543210"
}
```

| outResponseCode | Meaning |
|-----------------|---------|
| `00` | Success |
| `01` | Invalid card type |
| `02` | Invalid BIN |
| `11` | Invalid function |
| `13` | Invalid card template |
| `99` | DB/ internal error |

---

========================
## 10. DETAILED BUSINESS LOGIC (WORKING SERVICES ONLY)
========================

### `DebitCardIssuanceServiceImpl.debitCardIssuanceRequest()` ✅

| Line logic | Condition | Result |
|------------|-----------|--------|
| Load `Cfg_Card_Type` | Key = `inPartID + inCardType` | If null → code `01` |
| Check BIN | `strBin == null` | Code `02` |
| `validateFunction()` | NEWCARD must have empty `inCard` | Code `11` if wrong |
| BIN suffix | `getBinSuffixFlag()` | Append suffix to BIN |
| PAN generation | `card_gen_type = R` | Random 16-digit |
| PAN generation | else | `getLastSequenceNo()` sequential |
| Template | `Cfg_Card_Template` from cache | If null → code `13` |
| DAO call | Pass request + template | INSERT rows |
| Token schedule | `card_token == 1` on card type | Async token task |

### `DebitCardIssuanceDaoImpl` — NEWCARD ✅

| Step | SQL / action |
|------|----------------|
| 1 | `tokenCard = CardUtils.tokenCard()` |
| 2 | `INSERT card_details` — masked PAN, enc_card, limits, expiry |
| 3 | `INSERT card_plastic_details` — emboss data |
| 4 | If count > 1 → `outResponseCode = 00` |

### `CustomerAdditionServiceImpl.addCustomer()` ✅

| Step | Logic |
|------|-------|
| 1 | Validate address type in cache |
| 2 | Validate email type |
| 3 | Validate document type |
| 4 | Call SP `customerAddition` |

---

========================
## 11. DESIGN PATTERNS USED (IN WORKING CODE)
========================

| Pattern | Working example |
|---------|-----------------|
| Dependency Injection | `@Autowired` in all controllers/services/DAOs |
| Repository Pattern | `*DaoImpl` + `JdbcTemplate` |
| DTO Pattern | `*Request` / `*Response` models |
| Service Layer | `*ServiceImpl` between controller and DAO |
| Cache-Aside | EhCache for `cfg_*` — `refreshCache` reloads |
| Singleton | Spring default bean scope |

---

========================
## 12. PERFORMANCE & SCALABILITY (WORKING PARTS)
========================

| Feature | Status | Detail |
|---------|--------|--------|
| EhCache config cache | ✅ Working | Reduces DB reads on every issuance |
| Connection pool | ✅ Working | commons-dbcp, max 10 connections |
| Scheduler threads | ✅ Working | Token jobs off main thread |
| Pagination on search | ❌ | Returns all matches |
| Redis / multi-node | ❌ | Single-server cache only |

---

========================
## 13. COMPLETE EXECUTION FLOW (WORKING EXAMPLE: CARD ACTIVATION)
========================

### Real example: Activate card after issuance

```
① HTTP Request
   POST http://localhost:8045/CardManagementAPI/cardStatus
   Content-Type: application/json

② Controller: DebitCardIssuance.cardStatus()
   File: debitCardManagement/controller/DebitCardIssuance.java:63
   Input: CardStatusRequest JSON

③ Service: DebitCardIssuanceServiceImpl.cardStatusRequest()
   File: debitCardManagement/services/DebitCardIssuanceServiceImpl.java:191
   - validateStatus(participant, statusCode)
   - Checks ehCacheService.getCfg_Status().get(statusCode)
   - If status NOT in cache → return 99 "Card Status Not Found !!"

④ DAO: DebitCardIssuanceDaoImpl.getCardDetailsBasedOnCardNumber()
   File: debitCardManagement/dao/DebitCardIssuanceDaoImpl.java:528

   SQL 1: CHECK_CARD_COUNT_CARD_STATUS
   → COUNT(*) WHERE participant_id=? AND token_card=? AND member_number=?

   SQL 2: CHECK_CFG_STATUS_EXIST
   → SELECT id FROM cfg_status WHERE id=?

   SQL 3: UPDATE_CARD_DETAILS
   → UPDATE card_details SET card_status=? WHERE token_card=? AND participant_id=?

   SQL 4: INSERT_CARD_STATUS_HISTORY
   → INSERT INTO card_status_history (...)

⑤ Response built
   CardStatusResponse { outResponseCode: "00", message: "success" }

⑥ Exception path
   Controller catch → outResponseCode "99", "Internal Error"
```

---

========================
## 14. INTERVIEW EXPLANATION MODE (WORKING PROJECT ONLY)
========================

### "What does this project do?"
> "It is a Card Management System for banks. The working part covers the full **issuance lifecycle**: customer onboarding, debit card creation, activation, account linking, limit updates, and inquiry. It exposes REST APIs consumed by CBS or an operations UI. Card numbers are AES-encrypted in MySQL; operators only see masked PANs and internal token IDs."

### "What is actually working vs planned?"
> "**Working:** customer APIs, card issuance (NEW/INSTANT/REPLACE/RENEW), card status, account link, inquiry, limits, embossing, token scheduler. **Not working for production:** `cardAuthentication` REST endpoint — it returns dummy approvals. Real auth logic is in the DAO but not wired. HSM APIs work only with physical Thales hardware."

### "Architecture in one sentence"
> "Spring Boot monolith, JDBC + stored procedures, EhCache for configuration, no JPA, deployed as WAR on Tomcat port 8045."

### "Biggest technical challenge"
> "Mixed data access — some flows use direct SQL inserts (issuance), others use stored procedures (customer). Configuration must be pre-loaded in cache or every validation fails with 'Invalid Card Type'."

### "Security?"
> "PAN encryption at rest with AES; no API authentication layer. For interviews: I'd highlight encryption + audit (`card_status_history`) as strengths, and missing JWT/API security as improvement area."

---

========================
## 15. IMPROVEMENT SUGGESTIONS (TO MAKE NON-WORKING PARTS WORK)
========================

| Priority | Item | So that… |
|----------|------|----------|
| 1 | Wire `cardAuthentication` controller to `CardAuthenticationDaoImpl` | Real switch auth works |
| 2 | Fix `pinManagement` null response on error | No NPE on failures |
| 3 | Externalize DB credentials from `AppConfig.java` | Deploy on any machine |
| 4 | Add Spring Security + API key | APIs not open |
| 5 | Enable Swagger | API documentation works |
| 6 | Add SQL scripts to repo | New developers can create DB |
| 7 | Fix hardcoded file paths (`key.txt`, `emboss.xml`) | Works without `C:\CMS\...` |

---

========================
## 16. GENERATE DIAGRAMS
========================

### Working modules only

```
┌─────────────┐     ┌──────────────────┐     ┌─────────────┐
│   CBS/UI    │────▶│  CMS REST APIs   │────▶│   MySQL     │
└─────────────┘     │  (WORKING)       │     │cardmanagement│
                    │                  │     └─────────────┘
                    │ • Customer  ✅   │
                    │ • Issuance  ✅   │     ┌─────────────┐
                    │ • Status    ✅   │────▶│ Emboss File │
                    │ • Inquiry   ✅   │     └─────────────┘
                    │ • Account   ✅   │
                    │ • Limits    ✅   │
                    │ • Auth      ❌   │
                    └────────┬─────────┘
                             │ optional
                             ▼
                    ┌──────────────────┐
                    │  Thales HSM  ⚠️  │
                    └──────────────────┘
```

### Working request flow

```
POST /debitCardIssuance
        │
        ▼
DebitCardIssuance (Controller)
        │
        ▼
DebitCardIssuanceServiceImpl
   ├─ EhCache: cfg_card_type ✓
   ├─ EhCache: cfg_card_template ✓
   └─ CardNumberGenerator ✓
        │
        ▼
DebitCardIssuanceDaoImpl
   ├─ INSERT card_details ✓
   └─ INSERT card_plastic_details ✓
        │
        ▼
Response: 00 + outTokenCard
```

### Working activation flow

```
POST /cardStatus
        │
        ▼
validate cfg_status (cache)
        │
        ▼
UPDATE card_details.card_status
        │
        ▼
INSERT card_status_history
        │
        ▼
Response: 00
```

---

========================
## 17. CODE WALKTHROUGH (WORKING CLASSES)
========================

### `CardManagementServiceApplication` ✅
- Starts Spring Boot
- Entry point for Eclipse/Tomcat

### `AppConfig` ✅ (must configure)
| Bean | Purpose |
|------|---------|
| `dataSource` | MySQL connection pool |
| `jdbcTemplate` | All DAO access |
| `cacheManager` | EhCache |
| `embossFile` | JAXB load emboss.xml |
| `poolScheduler` | Token cron |

### `EhCacheServiceImpl` ✅
- `getCfg_Card_Type()`, `getCfg_Status()`, etc.
- `refreshCache()` — **call before testing**

### `DebitCardIssuance` (Controller) ✅
| Method | Working? |
|--------|----------|
| `debitCardIssuanceRequest` | ✅ |
| `cardStatus` | ✅ |
| `pinManagement` | ✅ (success path) |
| `NCMCServiceUpdation` | ✅ |
| `scheduleCardToken` / `getCardToken` | ✅ |

### `CustomerAddition` (Controller) ✅
All 5 POST methods + `refreshCache`

### `InquiryServices` (Controller) ✅
All 3 search APIs

---

========================
## 18. OUTPUT FORMAT + HOW TO RUN (WORKING SETUP)
========================

### Prerequisites checklist

| # | Requirement | Check |
|---|-------------|-------|
| 1 | MySQL running | ☐ |
| 2 | Database `cardmanagement` created | ☐ |
| 3 | All tables + stored procedures exist | ☐ |
| 4 | `cfg_participant`, `cfg_card_type`, `cfg_card_template` populated | ☐ |
| 5 | `cfg_status` has activation code (e.g. `1`) | ☐ |
| 6 | `key.txt` exists at path in `CardUtils.java` | ☐ |
| 7 | `emboss.xml` exists | ☐ |
| 8 | `AppConfig.java` DB URL/user/password correct | ☐ |
| 9 | Eclipse: Run `CardManagementServiceApplication` | ☐ |

### Postman test sequence (proves everything works)

```
1. GET  /refreshCache
2. POST /customerAddition        → save outcustomerid
3. POST /debitCardIssuance       → save outTokenCard
4. POST /cardStatus              → use outTokenCard
5. POST /accountManagement
6. POST /searchCardDetails
7. POST /embossRequest           → {"cardType":"1|01"}
```

### Response code quick reference (working APIs)

| Code | Meaning |
|------|---------|
| `00` | Success |
| `01`–`15` | Business validation error |
| `91` | Status change not allowed |
| `93` | Card not found |
| `99` | Internal / unhandled error |

---

## What is NOT working (do not document as production-ready)

| Feature | Reason |
|---------|--------|
| `POST /cardAuthentication` | Controller returns fake `00`; DAO not used |
| Swagger | `@EnableSwagger2` commented out |
| API security | No Spring Security |
| Visa ISO handler | TODO stubs, no REST endpoint |
| Maven CLI on your PC | `mvn` not in PATH — use Eclipse |

---

*Generated from codebase analysis of `cardManagementService` (TranEco). Last updated: May 2026.*
