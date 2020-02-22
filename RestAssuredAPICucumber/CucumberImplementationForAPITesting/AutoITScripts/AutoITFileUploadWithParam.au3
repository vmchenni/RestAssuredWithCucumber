WinWaitActive("Open")
ControlSend("Open","","Edit1",$CmdLine[1]);
Send("{ENTER}")