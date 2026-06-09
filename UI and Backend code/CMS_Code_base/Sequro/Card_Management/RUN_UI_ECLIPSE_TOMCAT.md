# Card Management System (CMS) — UI Run Guide

**Document:** Run UI on Eclipse + Tomcat 9  
**Project:** Card_Management  
**Version:** 1.0  
**Date:** June 2026  

---

## 1. Purpose

This document describes how to build, deploy, and run the **Card_Management** web application (operator UI) using **Eclipse IDE** and **Apache Tomcat 9**.

The UI is a **Java/JSP** application. It does **not** use Node.js or `npm install`.

---

## 2. Scope

| In scope | Out of scope |
|----------|----------------|
| UI project `Card_Management` | React/Angular/npm frontend |
| Eclipse import and Tomcat deploy | Production server hardening |
| Local developer setup | Full API development guide |

---

## 3. Project locations

| Item | Path |
|------|------|
| CMS workspace root | `C:\CMS` |
| UI project root | `C:\CMS\UI and Backend code\CMS_Code_base\Sequro\Card_Management` |
| REST API project | `C:\CMS\UI and Backend code\cardManagementService` |
| UI login URL (after deploy) | `http://localhost:8080/Card_Management/loginForm` |
| API base URL (required) | `http://localhost:8045/CardManagementAPI` |

---

## 4. Prerequisites

Complete all items before starting the UI.

| # | Requirement | Version / notes |
|---|-------------|-----------------|
| 1 | JDK | **8** (Java 1.8) |
| 2 | Eclipse IDE | **Enterprise Java and Web Developers** edition |
| 3 | Apache Tomcat | **9.x** |
| 4 | MySQL Server | Port **3306** (default) |
| 5 | Maven | For running `cardManagementService` (optional: Eclipse for API) |
| 6 | Database scripts | Import `traneco_cmsdb.sql`, `cardmanagement.sql` (under `Sequro` folder) |

---

## 5. Pre-run checklist (environment)

- [ ] JDK 8 installed and configured in Eclipse  
- [ ] Tomcat 9 downloaded and path known  
- [ ] MySQL service is running  
- [ ] Database `traneco_cmsdb` created and script imported  
- [ ] Database `cardmanagement` created and script imported (for API)  
- [ ] `database.properties` updated with correct MySQL username/password  
- [ ] `cardManagementService` API started on port **8045**  

---

## 6. Step-by-step procedure

### Step 1 — Start MySQL

1. Start the MySQL service on the local machine.  
2. Confirm connection on `localhost:3306`.  
3. Verify databases exist: `traneco_cmsdb`, `cardmanagement` (and others per `database.properties`).

---

### Step 2 — Configure application properties

**File (source):**  
`src\database.properties`

Update:

- `cms.db.URL`  
- `cms.db.UserName`  
- `cms.db.Password`  
- Other DB URLs as required  

**File (runtime — copy after edit):**  
`WebContent\WEB-INF\classes\database.properties`

**File (API URL — source):**  
`src\config.properties`

Ensure:

```properties
cms.url=http://localhost:8045/CardManagementAPI
```

**File (runtime — copy after edit):**  
`WebContent\WEB-INF\classes\config.properties`

> **Note:** Tomcat reads properties from `WebContent\WEB-INF\classes` at runtime. Always keep `src` and `WebContent\WEB-INF\classes` in sync after changes.

---

### Step 3 — Start backend API (required)

The UI calls the REST API for card operations (customer add, card issue, emboss, etc.).

1. Open Command Prompt or PowerShell.  
2. Run:

```powershell
cd "C:\CMS\UI and Backend code\cardManagementService"
mvn spring-boot:run
```

3. Wait until the application listens on port **8045**.  
4. Confirm base URL: `http://localhost:8045/CardManagementAPI`

---

### Step 4 — Install and open Eclipse

1. Install **Eclipse IDE for Enterprise Java and Web Developers**.  
2. Launch Eclipse.  
3. Select workspace (recommended): `C:\CMS`  
4. Click **Launch**.

---

### Step 5 — Import Card_Management project

1. Menu: **File → Import…**  
2. Select: **General → Existing Projects into Workspace**  
3. Click **Next**.  
4. **Select root directory:**

   `C:\CMS\UI and Backend code\CMS_Code_base\Sequro\Card_Management`

5. Ensure project **Card_Management** is checked.  
6. Do **not** check “Copy projects into workspace” (unless you intentionally want a copy).  
7. Click **Finish**.

**Expected result:** Project **Card_Management** appears in Project Explorer.

---

### Step 6 — Configure JDK 8 in Eclipse

1. Menu: **Window → Preferences**  
2. Navigate: **Java → Installed JREs**  
3. Add or select **JDK 8** as default.  
4. Click **Apply**.

**Project-level:**

1. Right-click **Card_Management → Properties**  
2. **Java Compiler** → Compliance level: **1.8**  
3. **Java Build Path → Libraries** → Use JDK 8 / Tomcat runtime  
4. Click **Apply and Close**

---

### Step 7 — Add Apache Tomcat 9 runtime

1. Menu: **Window → Preferences**  
2. Navigate: **Server → Runtime Environments**  
3. Click **Add…**  
4. Select: **Apache → Apache Tomcat v9.0**  
5. Click **Next**.  
6. **Tomcat installation directory:** path to Tomcat 9 (e.g. `C:\apache-tomcat-9.0.87`)  
7. **JRE:** JDK 8  
8. Click **Finish → Apply and Close**

---

### Step 8 — Create Tomcat server and deploy project

1. Open view: **Window → Show View → Servers**  
2. Right-click in Servers view → **New → Server**  
3. Select: **Apache Tomcat v9.0 Server**  
4. Choose the runtime created in Step 7 → **Next**  
5. Move **Card_Management** from **Available** to **Configured**  
6. Click **Finish**

**Set context path:**

1. Double-click the Tomcat server in Servers view  
2. In the server configuration editor, verify module **Card_Management**  
3. Context path (Path): **`/Card_Management`**  
4. Save (**Ctrl+S**)

---

### Step 9 — Build the project

1. Menu: **Project → Clean…**  
2. Select **Card_Management**  
3. Click **Clean**  
4. Open **Window → Show View → Problems**  
5. Resolve any **Errors** (warnings may remain)

**If compile errors mention missing Spring/JAR libraries:**

- Confirm **Apache Tomcat v9.0** is on the project build path.  
- Obtain missing libraries from the original project delivery (`WEB-INF\lib`) if not present in the repository.

---

### Step 10 — Start Tomcat

1. In **Servers** view, right-click **Tomcat v9.0 Server**  
2. Click **Start**  
3. Monitor **Console** for “Server startup” without fatal errors  

**Default HTTP port:** `8080` (per project `server.xml`)

---

### Step 11 — Access the application

1. Open a web browser.  
2. Navigate to:

   **http://localhost:8080/Card_Management/loginForm**

3. Log in with a user defined in database **traneco_cmsdb**.

**Alternative entry points:**

| URL | Screen |
|-----|--------|
| `http://localhost:8080/Card_Management/` | Redirects to login |
| `http://localhost:8080/Card_Management/home` | Home (after login) |

---

### Step 12 — Stop the application

1. In Eclipse **Servers** view, right-click Tomcat server  
2. Click **Stop**  
3. Stop `cardManagementService` (Ctrl+C in the terminal running Maven)  
4. Stop MySQL if no longer needed  

---

## 7. Daily run sequence (summary)

| Order | Action |
|-------|--------|
| 1 | Start MySQL |
| 2 | Start `cardManagementService` (port 8045) |
| 3 | Start Tomcat with **Card_Management** in Eclipse |
| 4 | Open `http://localhost:8080/Card_Management/loginForm` |

---

## 8. Verification

| Check | Expected result |
|-------|-----------------|
| Tomcat Console | No `SEVERE` errors during startup |
| Login page | CMS logo, username, password, participant dropdown |
| API | `cardManagementService` running on 8045 |
| Home (after login) | Client search form and menu bar |

---

## 9. Alternative: run UI with Maven (no Eclipse Tomcat setup)

A `pom.xml` is included in this project to download all Java libraries (the repo does not contain `WEB-INF\lib` JAR files).

**Terminal 1 — API:**

```powershell
cd "C:\CMS\UI and Backend code\cardManagementService"
mvn spring-boot:run
```

**Terminal 2 — UI:**

```powershell
cd "C:\CMS\UI and Backend code\CMS_Code_base\Sequro\Card_Management"
mvn clean package
mvn tomcat9:run-war
```

**Login URL:** http://localhost:8080/Card_Management/loginForm

**One-click script (both):** run `C:\CMS\UI and Backend code\START_CMS.ps1` in PowerShell.

---

## 10. Troubleshooting

| Symptom | Possible cause | Action |
|---------|----------------|--------|
| **`ERR_CONNECTION_REFUSED` on :8080** | **Tomcat not running** | Start Tomcat in Eclipse (**Start**, not Stopped) OR run `mvn tomcat9:run-war` |
| `npm ENOENT package.json` | Wrong technology assumed | Do not use npm; follow this document |
| HTTP 404 | Wrong context path | Set module path to `/Card_Management` |
| Port 8080 in use | Another Tomcat/service | Stop other process or change Tomcat port |
| Login / DB error | MySQL or credentials | Fix `database.properties` in `WEB-INF\classes` |
| Card operations fail | API not running | Start `cardManagementService` on 8045 |
| Stale config | Properties not copied | Sync `src` → `WebContent\WEB-INF\classes`; `cms.url` must be **8045** |
| Many compile errors in Eclipse | Missing JARs | Use **Maven** `mvn package` (see section 9) or restore `WEB-INF\lib` from full delivery |
| `netstat` shows nothing on 8080 | Server not started | Start Tomcat / Maven tomcat plugin |

---

## 11. Related configuration files

| File | Purpose |
|------|---------|
| `src\database.properties` | MySQL connection for UI |
| `src\config.properties` | REST API URLs (`cms.url`, URI paths) |
| `src\log4j.properties` | Logging |
| `WebContent\WEB-INF\resources\jsp\` | JSP screens |
| `server.xml` | Tomcat reference (context `/Card_Management`) |

---

## 12. Document revision history

| Version | Date | Changes |
|---------|------|---------|
| 1.0 | June 2026 | Initial UI run guide (Eclipse + Tomcat 9) |
| 1.1 | June 2026 | Added Maven `pom.xml` and connection-refused fixes |

---

## 13. Support information

For build errors, provide:

1. Eclipse **Problems** view screenshot or error list  
2. **Console** output from Tomcat startup (last 30 lines)  
3. Confirmation that MySQL and API (8045) are running  

---

**End of document**
