;This file will be executed next to the application bundle image
;I.e. current directory will contain folder googlesearch with application files
[Setup]
AppId={{fxApplication}}
AppName=googlesearch
AppVersion=1.0
AppVerName=googlesearch 1.0
AppPublisher=user
AppComments=google
AppCopyright=Copyright (C) 2018
;AppPublisherURL=http://java.com/
;AppSupportURL=http://java.com/
;AppUpdatesURL=http://java.com/
DefaultDirName={localappdata}\googlesearch
DisableStartupPrompt=Yes
DisableDirPage=No
DisableProgramGroupPage=Yes
DisableReadyPage=Yes
DisableFinishedPage=Yes
DisableWelcomePage=Yes
DefaultGroupName=user
;Optional License
LicenseFile=
;WinXP or above
MinVersion=0,5.1 
OutputBaseFilename=googlesearch-1.0
Compression=lzma
SolidCompression=yes
PrivilegesRequired=lowest
SetupIconFile=googlesearch\googlesearch.icoUninstallDisplayIcon={app}\googlesearch.ico
UninstallDisplayName=googlesearch
WizardImageStretch=No
;WizardSmallImageFile=googlesearch-setup-icon.bmp   
ArchitecturesInstallIn64BitMode=x64


[Languages]
Name: "korean"; MessagesFile: "compiler:Languages\Korean.isl"

[Files]
Source: "googlesearch\googlesearch.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "googlesearch\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\googlesearch"; Filename: "{app}\googlesearch.exe"; IconFilename: "{app}\googlesearch.ico"; Check: returnTrue()
Name: "{commondesktop}\googlesearch"; Filename: "{app}\googlesearch.exe";  IconFilename: "{app}\googlesearch.ico"; Check: returnFalse()


[Run]
Filename: "{app}\googlesearch.exe"; Parameters: "-Xappcds:generatecache"; Check: returnFalse()
Filename: "{app}\googlesearch.exe"; Description: "{cm:LaunchProgram,googlesearch}"; Flags: nowait postinstall skipifsilent; Check: returnTrue()
Filename: "{app}\googlesearch.exe"; Parameters: "-install -svcName ""googlesearch"" -svcDesc ""googlesearch"" -mainExe ""googlesearch.exe""  "; Check: returnFalse()

[UninstallRun]
Filename: "{app}\googlesearch.exe "; Parameters: "-uninstall -svcName googlesearch -stopOnUninstall"; Check: returnFalse()

[Code]
function returnTrue(): Boolean;
begin
  Result := True;
end;

function returnFalse(): Boolean;
begin
  Result := False;
end;

function InitializeSetup(): Boolean;
begin
// Possible future improvements:
//   if version less or same => just launch app
//   if upgrade => check if same app is running and wait for it to exit
//   Add pack200/unpack200 support? 
  Result := True;
end;  
