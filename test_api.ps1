$baseUrl = "http://localhost:1515"
$apiKey = "7059772e-edd5-4bec-b7af-bfdd75af7385"
$headers = @{ "x-api-key" = $apiKey; "Content-Type" = "application/json" }

Write-Host "Waiting for server to start on port 1515..."
$retries = 60
while ($retries -gt 0) {
    try {
        $tcp = Test-NetConnection -ComputerName localhost -Port 1515 -InformationLevel Quiet
        if ($tcp) { 
            Write-Host "Server is up!"
            break 
        }
    } catch {}
    Start-Sleep -Seconds 2
    $retries--
    Write-Host "." -NoNewline
}
if ($retries -eq 0) {
    Write-Error "Server failed to start in time."
    exit 1
}

Write-Host "`n1. Testing Login..."
try {
    $loginBody = '{"email":"terryglymin@gmail.com", "password":"android1", "pass":"android1"}'
    $loginResponse = Invoke-RestMethod -Method Post -Uri "$baseUrl/v1/auth_service/user_login" -Body $loginBody -Headers $headers
    Write-Host "Login Response: "
    if ($loginResponse -is [string]) {
        Write-Host $loginResponse
    } else {
        Write-Host ($loginResponse | ConvertTo-Json -Depth 5)
    }
} catch {
    Write-Host "Login Failed: $_"
}

Write-Host "`n2. Testing System Dashboard Data..."
try {
    $dashBody = '{"start_date":"2024-01-01", "end_date":"2025-12-31"}'
    $dashResponse = Invoke-RestMethod -Method Post -Uri "$baseUrl/tickets_mgt_services/get_system_dashboard_data" -Body $dashBody -Headers $headers
    Write-Host "Dashboard Response: "
    if ($dashResponse -is [string]) {
        Write-Host $dashResponse
    } else {
        Write-Host ($dashResponse | ConvertTo-Json -Depth 5)
    }
} catch {
    Write-Host "Dashboard Request Failed: $_"
}

Write-Host "`n3. Testing Tickets List..."
try {
    $ticketsBody = '{"start_date":"2024-01-01", "end_date":"2025-12-31"}'
    $ticketsResponse = Invoke-RestMethod -Method Post -Uri "$baseUrl/tickets_mgt_services/get_tickets_list_for_dashboard" -Body $ticketsBody -Headers $headers
    Write-Host "Tickets List Response: "
    if ($ticketsResponse -is [string]) {
        Write-Host $ticketsResponse
    } else {
        Write-Host ($ticketsResponse | ConvertTo-Json -Depth 5)
    }
} catch {
    Write-Host "Tickets List Request Failed: $_"
}

Write-Host "`n4. Testing User Org Dashboard Data..."
try {
    # Ensure all values are strings if the backend expects a JSON string or properly typed JSON
    $orgDashBody = '{"user_id":"d188baa6-d25e-4b13-a095-e9f08a699862", "organization_id":"28758830-84a3-4a70-af71-d9e55762fddc", "from_date":"2024-01-01", "to_date":"2025-12-31", "limit": 10, "offset": 0}'
    $orgDashResponse = Invoke-RestMethod -Method Post -Uri "$baseUrl/tickets_mgt_services/get_user_org_dashboard_data" -Body $orgDashBody -Headers $headers
    Write-Host "User Org Dashboard Response: "
    if ($orgDashResponse -is [string]) {
        Write-Host $orgDashResponse
    } else {
        Write-Host ($orgDashResponse | ConvertTo-Json -Depth 5)
    }
} catch {
    Write-Host "User Org Dashboard Request Failed: $_"
}

Write-Host "`n5. Testing User Profile..."
try {
    $profileBody = '{"user_id":1}'
    $profileResponse = Invoke-RestMethod -Method Post -Uri "$baseUrl/v1/user_service/get_user_profile" -Body $profileBody -Headers $headers
    Write-Host "User Profile Response: "
    if ($profileResponse -is [string]) {
        Write-Host $profileResponse
    } else {
        Write-Host ($profileResponse | ConvertTo-Json -Depth 5)
    }
} catch {
    Write-Host "User Profile Request Failed: $_"
}
