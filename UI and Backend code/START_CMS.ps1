# CMS local startup: API (8045) + UI (8080)
$ErrorActionPreference = "Stop"

$root = Split-Path -Parent $MyInvocation.MyCommand.Path
$apiDir = Join-Path $root "cardManagementService"
$uiDir = Join-Path $root "CMS_Code_base\Sequro\Card_Management"

Write-Host "=== CMS Startup ===" -ForegroundColor Cyan
Write-Host "API: $apiDir"
Write-Host "UI:  $uiDir"
Write-Host ""

# 1) Build API
Write-Host "[1/3] Building cardManagementService..." -ForegroundColor Yellow
Push-Location $apiDir
mvn -q -DskipTests compile
if ($LASTEXITCODE -ne 0) { Pop-Location; throw "API build failed" }
Pop-Location

# 2) Start API in new window
Write-Host "[2/3] Starting API on http://localhost:8045/CardManagementAPI ..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$apiDir'; mvn spring-boot:run"

Start-Sleep -Seconds 15

# 3) Build and run UI on Tomcat 8080
Write-Host "[3/3] Starting UI on http://localhost:8080/Card_Management/loginForm ..." -ForegroundColor Yellow
Push-Location $uiDir
mvn -q -DskipTests package
if ($LASTEXITCODE -ne 0) { Pop-Location; throw "UI build failed" }
mvn tomcat9:run-war
Pop-Location
