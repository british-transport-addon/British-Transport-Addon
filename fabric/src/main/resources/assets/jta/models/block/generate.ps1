# Define the sign_type variable with a default value of black_
$sign_type = "steel_"

# Change this to customize the sign_type
# $sign_type = "blue_"

# Function to update file content
function Update-FileContent
{
    param (
        [string]$filename
    )

    $tempfile = "$filename.tmp"

    # Replace rusty_ with sign_type in the file content
    Get-Content $filename | ForEach-Object {
        $_ -replace 'rusty_', $sign_type
    } | Set-Content $tempfile

    # Move the temporary file to the original file
    Move-Item -Path $tempfile -Destination $filename -Force
}


Write-Host "Starting in parent/models/block directory"
Set-Location $PSScriptRoot
Pause

# Iterate over all .json files in the current directory (parent/models/block)
foreach ($file in Get-ChildItem *.json)
{
    $filename = $file.BaseName
    $extension = $file.Extension

    if ( $filename.StartsWith("rusty_"))
    {
        # Extract the part of the filename after rusty_
        $new_name = $filename.Substring(6)
        $new_file = "$sign_type$new_name$extension"

        # Create new file with the new sign_type prefix
        Write-Host "Copying $( $file.Name ) to $new_file"
        Copy-Item -Path $file.FullName -Destination $new_file -Force

        # Update the file content
        Write-Host "Updating content in $new_file"
        Update-FileContent $new_file
    }
}
Pause

# Go up one directory to parent/models
Write-Host "Moving to parent/models directory"
Set-Location ..

# Iterate over all .json files in the item directory (parent/models/item)
foreach ($file in Get-ChildItem "item\*.json")
{
    $filename = $file.BaseName
    $extension = $file.Extension

    if ( $filename.StartsWith("rusty_"))
    {
        # Extract the part of the filename after rusty_
        $new_name = $filename.Substring(6)
        $new_file = "item\$sign_type$new_name$extension"

        # Create new item model file with the new sign_type prefix
        Write-Host "Copying $( $file.Name ) to $new_file"
        Copy-Item -Path $file.FullName -Destination $new_file -Force

        # Update the file content
        Write-Host "Updating content in $new_file"
        Update-FileContent $new_file
    }
}
Pause

# Go up two directories to parent
Write-Host "Moving up to parent directory"
Set-Location ..\

# Change directory to blockstates (parent/blockstates)
Write-Host "Changing directory to parent/blockstates"
Set-Location blockstates
Pause

# Iterate over all blockstate files (parent/blockstates)
foreach ($file in Get-ChildItem *.json)
{
    $filename = $file.BaseName
    $extension = $file.Extension

    if ( $filename.StartsWith("rusty_"))
    {
        # Extract the part of the filename after rusty_
        $new_name = $filename.Substring(6)
        $new_file = "$sign_type$new_name$extension"

        # Create new blockstate file with the new sign_type prefix
        Write-Host "Copying $( $file.Name ) to $new_file"
        Copy-Item -Path $file.FullName -Destination $new_file -Force

        # Update the file content
        Write-Host "Updating content in $new_file"
        Update-FileContent $new_file
    }
}
Pause

Write-Host "Files have been copied and updated successfully."
