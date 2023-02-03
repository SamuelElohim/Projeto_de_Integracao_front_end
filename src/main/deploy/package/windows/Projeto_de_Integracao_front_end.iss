;This file will be executed next to the application bundle image
;I.e. current directory will contain folder Projeto_de_Integracao_front_end with application files
[Setup]
AppId={{MainClass}}
AppName=Projeto_de_Integracao_front_end
AppVersion=1.0
AppVerName=Projeto_de_Integracao_1.0
AppPublisher=Eletra Energy Solutions
AppComments=Projeto_de_Integracao_front_end
AppCopyright=Copyright (C) 2023
;AppPublisherURL=http://java.com/
;AppSupportURL=http://java.com/
;AppUpdatesURL=http://java.com/
DefaultDirName={localappdata}\Projeto_de_Integracao_front_end
DisableStartupPrompt=Yes
DisableDirPage=Yes
DisableProgramGroupPage=Yes
DisableReadyPage=Yes
DisableFinishedPage=Yes
DisableWelcomePage=Yes
DefaultGroupName=Eletra Energy Solutions
;Optional License
LicenseFile=
;WinXP or above
MinVersion=0,5.1 
OutputBaseFilename=Projeto_de_Integracao_front_end-1.0
Compression=lzma
SolidCompression=yes
PrivilegesRequired=lowest
SetupIconFile=Projeto_de_Integracao_front_end\Projeto_de_Integracao_front_end.ico
UninstallDisplayIcon={app}\Projeto_de_Integracao_front_end.ico
UninstallDisplayName=Projeto_de_Integracao_front_end
WizardImageStretch=yes
WizardSmallImageFile=Projeto_de_Integracao_front_end-setup-icon.bmp   
ArchitecturesInstallIn64BitMode=x64


[Languages]
Name: "brazilianportuguese"; MessagesFile: "compiler:Languages\BrazilianPortuguese.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "Projeto_de_Integracao_front_end\Projeto_de_Integracao_front_end.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "Projeto_de_Integracao_front_end\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\Projeto_de_Integracao_front_end"; Filename: "{app}\Projeto_de_Integracao_front_end.exe"; IconFilename: "{app}\Projeto_de_Integracao_front_end.ico"; Check: returnTrue()
Name: "{commondesktop}\Projeto_de_Integracao_front_end"; Filename: "{app}\Projeto_de_Integracao_front_end.exe";  IconFilename: "{app}\Projeto_de_Integracao_front_end.ico"; Check: returnFalse()
Name: "{commondesktop}\Projeto_de_Integracao_front_end"; Filename: "{app}\Projeto_de_Integracao_front_end"; Tasks: desktopicon


[Run]
Filename: "{app}\Projeto_de_Integracao_front_end.exe"; Parameters: "-Xappcds:generatecache"; Check: returnFalse()
Filename: "{app}\Projeto_de_Integracao_front_end.exe"; Description: "{cm:LaunchProgram,Projeto_de_Integracao_front_end}"; Flags: nowait postinstall skipifsilent; Check: returnTrue()
Filename: "{app}\Projeto_de_Integracao_front_end.exe"; Parameters: "-install -svcName ""Projeto_de_Integracao_front_end"" -svcDesc ""Projeto_de_Integracao_front_end"" -mainExe ""Projeto_de_Integracao_front_end.exe""  "; Check: returnFalse()

[UninstallRun]
Filename: "{app}\Projeto_de_Integracao_front_end.exe "; Parameters: "-uninstall -svcName Projeto_de_Integracao_front_end -stopOnUninstall"; Check: returnFalse()

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
