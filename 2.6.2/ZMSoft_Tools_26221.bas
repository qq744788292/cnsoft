Attribute VB_Name = "ZMSoft_Tools_26221"
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
'���ݿ���CN-Soft(ԭJFP)��ܴ���Ƭ�����ɹ���
'
'�汾 2.6.2.1 2019-10-06
'
'���� ZM-Soft
'
'��־˵��
'
'   2.6.2.2.1 2019-10-10 �Ż�Listҳ���߼������������չ�ֶΣ��Ż����·��
'   2.6.2.1   2019-09-29 �Ż�JSP���ҳ���߼����������meno�ֶ�
'   2.6.2     2019-09-29 ���JSP���ҳ�棨��ʡ70%������д��
'   2.6.1     2019-09-16 ������������ԴЭ��������ͬ����ܰ汾��
'   2.5.2     2019-07-17 ����ļ��������⣬ͬ����ܰ汾��
'   2.3.6     2019-05-10 �Ż�ECCodeMessageConstants����
'   2.3.5     2019-03-25 ����CreatAll���
'   2.3.4     2019-02-25 ��������ļ����
'   2.3.3     2018-12-02 ��������ļ����
'   2.3.2     2018-11-09 bug����
'   2.3.1     2018-11-07 ͳһApiDoc����淶
'   2.2.2     2018-11-05 ͳһController�ӿ������淶
'   2.1.1     2018-10-22 ȫ���װBusinesslogic��Controller��Dao��DBO��Mapper
'   2.0.2     2018-08-25 �ļ�����������
'   2.0.1     2018-08-24 ����StringBoot
'
'
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
'��ʼ�к�
Const dataStartLine = 7
'�����к�
Const dataEndLine = 100
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
'�ֶ�����(����)
Const itemNameCol = 3
'��ѯ����
Const paramNameCol = 18
'�б�չʾ
Const listItemNameCol = 19
'�ֶ���(Ӣ��)
Const fieldNameCol = 20
'�ֶ�����
Const propertiesCol = 31
'����
Const numberCol = 36
'����
Const keyCol = 38
'����
Const mustCol = 40
'ģ����ѯ
Const searchCol = 42
'Ĭ��ֵ
Const defaultCol = 45
'��ע
Const menoCol = 52

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Const JAVA_PATH = ""
Const WK_Stop_Str = "memo"
Const WK_Del_Flag = "delFlag"

Const PRI_KEY = "id"

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
'ͷ���ݱ���
Dim SYSNAME        As String
Dim spgname        As String
Dim sdbname        As String
Dim sfxdate        As String
Dim sver           As String
Dim sclName        As String
Dim sfileName      As String

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
'�����ݱ���
Dim itemValue           As String
Dim paramValue          As String
Dim listItemValue       As String
Dim fieldValue          As String
Dim fieldULValue        As String
Dim numberValue         As String
Dim mustValue           As String
Dim propertiesValue     As String
Dim keyValue            As String
Dim searchValue         As String
Dim defaultValue        As String
Dim menoValue           As String
    
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Dim SelectPath          As String
Dim creatAllSheet       As Boolean
'���н��
Dim result              As Boolean

Dim tempFilename        As String

''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Sub CreatAll()
    
    creatAllSheet = True
    
    Dim sh1 As Worksheet
    For i = 4 To ActiveWorkbook.Worksheets.Count

        Set sh1 = ActiveWorkbook.Worksheets(i)
        sh1.Activate
        
        Call CreatSQL
        
        Call CreatBean
        
        Call CreatXML
        
        Call CreatJSP
    
    Next
    
    MsgBox ("�ļ���������")
    
End Sub
    
Function Utf8WithoutBom(ByVal getPath As String, ByVal putPath As String)
    
    Dim getFileNum As Integer
    Dim putFileNum As Integer
    getFileNum = 1
    putFileNum = 2

    Open getPath For Binary As #getFileNum
    Open putPath For Binary As #putFileNum
    
    Dim fileByte As Byte
    Seek #getFileNum, 4
    
    For i = 1 To LOF(getFileNum) - 3
        Get #getFileNum, , fileByte
        Put #putFileNum, , fileByte
    Next i
    
    Close #getFileNum
    Close #putFileNum
End Function
    
Sub CreatField()

On Error GoTo Open_Error
   
    Dim WK_Str      As String
    Dim i           As Integer
    Dim strKeyName      As String
    Dim strKeyComm      As String
    
    Dim strItem          As String
    Dim strUpdateVal     As String
    Dim strInsertVal     As String
    Dim strWhereVal      As String
    
    Application.ScreenUpdating = False
       
    'Head .............
    For i = dataStartLine To 100     '��ʼѭ��
        WK_Str = ""
        itemValue = Cells(i, 10).Value
        If itemValue <> "" Then
            Cells(i, 45).Value = getPinyinValue(itemValue)
        End If
        
    Next i
   
    
    MsgBox ("ִ������")
    
    Exit Sub
    
Open_Error:
    MsgBox ("���ٴ�����")
End Sub

'''
'''��ú��ֵ�ƴ����
'''
Function getPinyinValue(ByVal x As String) As String
On Error Resume Next
Dim i As Integer
For i = 1 To Len(x)
    If Mid(x, i, 1) <> " " And Asc(Mid(x, i, 1)) < 0 Then getPinyinValue = getPinyinValue & Mid("ABCDEFGHJKLMNOPQRSTWXYZZ", Evaluate("MATCH(" & Asc(Mid(x, i, 1)) & ",{-20319,-20283,-19775,-19218,-18710,-18526,-18239,-17922,-17417,-16474,-16212,-15640,-15165,-14922,-14914,-14630,-14149,-14090,-13318,-12838,-12556,-11847,-11055,-10247},1)"), 1)
Next
End Function


Function getSQLValue(line As Integer) As Boolean
       
    itemValue = Trim(Cells(line, itemNameCol).Value)
    paramValue = Trim(Cells(line, paramNameCol).Value)
    listItemValue = Trim(Cells(line, listItemNameCol).Value)
    fieldValue = Trim(Cells(line, fieldNameCol).Value)
    '�շ�ת��
    fieldULValue = ULCase(fieldValue)
    numberValue = Trim(Cells(line, numberCol).Value)
    propertiesValue = UCase(Trim(Cells(line, propertiesCol).Value))
    mustValue = UCase(Trim(Cells(line, mustCol).Value))
    keyValue = UCase(Trim(Cells(line, keyCol).Value))
    
    searchValue = UCase(Trim(Cells(line, searchCol).Value))
    
    defaultValue = Trim(Cells(line, defaultCol).Value)
    
    menoValue = Trim(Cells(line, menoCol).Value)

    If fieldValue = "" Then
        getSQLValue = False
    Else
        fieldValue = UCase(fieldValue)
        getSQLValue = True
    End If
    
End Function

Function getEntityValue(line As Integer) As Boolean
       
    itemValue = Trim(Cells(line, itemNameCol).Value)
    paramValue = Trim(Cells(line, paramNameCol).Value)
    listItemValue = Trim(Cells(line, listItemNameCol).Value)
    fieldValue = Trim(Cells(line, fieldNameCol).Value)
    '�շ�ת��
    fieldULValue = ULCase(fieldValue)
    numberValue = Trim(Cells(line, numberCol).Value)
    propertiesValue = LCase(Trim(Cells(line, propertiesCol).Value))
    
    '�������Ͷ���
    changePropertiesValue (propertiesValue)
    
    mustValue = UCase(Trim(Cells(line, mustCol).Value))
    keyValue = UCase(Trim(Cells(line, keyCol).Value))
    
    searchValue = UCase(Trim(Cells(line, searchCol).Value))
    
    defaultValue = Trim(Cells(line, defaultCol).Value)
    
    menoValue = Trim(Cells(line, menoCol).Value)

    If fieldValue = "" Then
        getEntityValue = False
    Else
        fieldValue = upFirstStr(fieldValue)
        getEntityValue = True
    End If
    
End Function
'''
'''�������Ͷ���
'''
Function changePropertiesValue(properties As String) As Boolean
    If InStr(propertiesValue, "tinyint") > 0 Then
        propertiesValue = "Long"
    ElseIf InStr(propertiesValue, "int") > 0 Then
        propertiesValue = "Long"
    ElseIf InStr(propertiesValue, "bigint") > 0 Then
        propertiesValue = "Long"
    ElseIf InStr(propertiesValue, "float") > 0 Then
        propertiesValue = "Double"
    ElseIf InStr(propertiesValue, "double") > 0 Then
        propertiesValue = "Double"
    ElseIf InStr(propertiesValue, "decimal") > 0 Then
        propertiesValue = "BigDecimal"
        
    ElseIf InStr(propertiesValue, "datetime") > 0 Then
        propertiesValue = "Timestamp"
    ElseIf InStr(propertiesValue, "date") > 0 Then
        propertiesValue = "Date"
        
    Else
        propertiesValue = "String"
    End If
    
    
    changePropertiesValue = True
        
End Function

Function ULCase(SynStr As String) As String
    If SynStr <> "" Then
         ULCase = LCase(SynStr)
         Do While InStr(ULCase, "_") > 0
             ULCase = Left(ULCase, InStr(ULCase, "_") - 1) & upFirstStr(Right(ULCase, Len(ULCase) - InStr(ULCase, "_")))
         Loop
     End If
End Function
Function upFirstStr(SynStr As String) As String
     upFirstStr = UCase(Left(SynStr, 1)) & (Right(SynStr, Len(SynStr) - 1))
End Function

Function downFirstStr(SynStr As String) As String
     downFirstStr = LCase(Left(SynStr, 1)) & (Right(SynStr, Len(SynStr) - 1))
End Function

Sub WriteUTF8()
    Dim WriteStream As Object
    Set WriteStream = CreateObject("ADODB.Stream") '�ļ������ģʽ
    WriteStream.Type = 2     'adTypeText
    WriteStream.Charset = "UTF-8"
    WriteStream.Open
    
    
    WriteStream.WriteText "���utf-8" & vbCrLf
    WriteStream.WriteText "���utf-8" & vbCrLf
    WriteStream.WriteText "���utf-8" & vbCrLf
    WriteStream.WriteText "���utf-8" & vbCrLf
    WriteStream.WriteText "���utf-8" & vbCrLf
    
    WriteStream.SaveToFile ThisWorkbook.Path & "\1.txt", 2  'adSaveCreateOverWrite
    
    
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
End Sub


Sub targetFileName()
    SYSNAME = Cells(1, 52).Value
    spgname = Cells(1, 31).Value
    sdbname = SYSNAME & Cells(1, 62).Value
    'sver = Cells(4, 78).Value
       
    sfxdate = Format(Date, "YYYY/MM/DD")

    Dim sssss As String
    sssss = "��ѡ���ļ����·��" 'upFirstStr(ULCase(sdbname))
    '����ļ�����
    If (SelectPath = "") Or creatAllSheet = False Then
        SelectPath = Application.GetSaveAsFilename(sssss)
        'Application.GetSaveAsFilename(InitialFilename:=sssss & "", fileFilter:=" , *.")
    End If
    
    If (SelectPath = "") Then
        sfileName = "False"
        MsgBox ("��ѡ���ļ����·��")
        Exit Sub
    End If
    
    
    If (sdbname = "") Then
        sfileName = "False"
        Exit Sub
    Else
        Dim index As Integer
        index = InStrRev(SelectPath, "\")
        
        SelectPath = Left(SelectPath, InStrRev(SelectPath, "\"))
    
    End If
        
    Dim FolderPath As String
    FolderPath = upFirstStr(ULCase(sdbname))
    sfileName = SelectPath + FolderPath
    If Dir(sfileName, vbDirectory) = "" Then
        MkDir sfileName
    End If
    
    SelectPath = SelectPath + FolderPath
    sfileName = sfileName & "\" & FolderPath
    
End Sub
''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Sub CreatSQL()

    Call targetFileName
    If sfileName = "False" Then
        Exit Sub
    End If
On Error GoTo Open_Error
    
    Dim soutfileName As String
    Dim sKeyName As String
    Dim index As Integer
    
    sKeyName = sfileName
    index = InStr(sKeyName, "\")
    While index > 0
        sKeyName = Mid(sKeyName, index + 1)
        index = InStr(sKeyName, "\")
    Wend
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'Open sfileName & ".sql" For Output Shared As #1
    soutfileName = sfileName & ".sql"
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Dim WriteStream As Object
    Set WriteStream = CreateObject("ADODB.Stream") '�ļ������ģʽ
    WriteStream.Type = 2     'adTypeText
    WriteStream.Charset = "UTF-8"
    WriteStream.Open
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    
    Dim WK_Str      As String
    Dim i           As Integer
    Dim strKeyName      As String
    Dim strKeyComm      As String
    
    Dim strItem          As String
    Dim strUpdateVal     As String
    Dim strInsertVal     As String
    Dim strWhereVal      As String
    
    Application.ScreenUpdating = False
    
    WriteStream.WriteText "CREATE TABLE " & LCase(sdbname) & vbCrLf
    WriteStream.WriteText "(" & vbCrLf
    
    'Head .............
    For i = dataStartLine To dataEndLine     '��ʼѭ��
        WK_Str = ""
        If Not getSQLValue(i) Then
            Exit For
        End If
    
        '/**********����ֶ���Ŀ**********/
        'SQLID VARCHAR2(200) DEFAULT 0
        If fieldValue <> "" Then
            If strItem <> "" Then
                strItem = strItem & " , " & fieldValue
                strInsertVal = strInsertVal & " , " & "?"
                strUpdateVal = strUpdateVal & " ; " & fieldValue & " = ?"
            Else
                strItem = fieldValue
                strInsertVal = "?"
                strUpdateVal = fieldValue & " = ?"
            End If
        End If
        
        '�ؼ���
        If keyValue <> "" Then
            If strKeyName <> "" Then
                strKeyName = strKeyName & " , " & fieldValue
                strWhereVal = strWhereVal & " ; " & fieldValue & " = ?"
            Else
                strKeyName = fieldValue
                strWhereVal = fieldValue & " = ?"
            End If
            
            mustValue = "Y"
            Range("AO" & i).Select   '& ":AQ" & i
            ActiveCell.FormulaR1C1 = "Y"
        End If
        
        WK_Str = "    " & LCase(fieldValue)
        
        '����
        If propertiesValue = "DATE" Or propertiesValue = "TIMESTAMP" Then
            WK_Str = WK_Str & " " & "DATETIME"
        ElseIf propertiesValue = "TEXT" Then
            WK_Str = WK_Str & " " & "TEXT"
        ElseIf propertiesValue = "VARCHAR2" Then
            WK_Str = WK_Str & " " & "VARCHAR" & "(" & numberValue & ")"
        Else
            WK_Str = WK_Str & " " & propertiesValue & "(" & numberValue & ")"
        End If
        
        'Ĭ��ֵ
        If defaultValue <> "" Then
            If keyValue <> "" Then
            
            Else
                WK_Str = WK_Str & " DEFAULT '" & defaultValue & "'"
            End If
        End If

        '��
        If mustValue <> "" Then
            If keyValue <> "" Then
                WK_Str = WK_Str & " NOT NULL"   'unsigned NOT NULL AUTO_INCREMENT
            Else
                WK_Str = WK_Str & " NOT NULL"
            End If
        End If

        'MySQL
        WK_Str = WK_Str & " COMMENT " & "'" & itemValue & "'"

        'SQL
        WK_Str = WK_Str & ","
        WriteStream.WriteText WK_Str & vbCrLf
        
    Next i
    
    '����
    If strKeyName = "" Then
        strKeyName = PRI_KEY
        strKeyComm = "COMMENT ON COLUMN " & LCase(sdbname) & "." & strKeyName & " IS 'SQL'"
        WriteStream.WriteText "    Puk VARCHAR2(12) NOT NULL," & vbCrLf
    End If
    
    'WriteStream.WriteText "    -- ����" & vbCrLf
    WK_Str = "PRIMARY KEY (" & LCase(strKeyName) & ")"
    WriteStream.WriteText WK_Str & vbCrLf
    
    WriteStream.WriteText ")" & " COMMENT " & "'" & spgname & "'" & vbCrLf
    
    WriteStream.WriteText ";" & vbCrLf
    
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    tempFilename = SelectPath + "temp.zm"
    WriteStream.SaveToFile tempFilename, 2  'adSaveCreateOverWrite
    
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    
    'ȥ��BOMͷ
    Call Utf8WithoutBom(tempFilename, soutfileName)
    
    'ɾ����ʱ�ļ�
    Kill tempFilename

    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    If creatAllSheet = False Then
        MsgBox ("�ļ���������")
    End If
    
    Exit Sub
    
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & "���ٴ�����")
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
End Sub


Sub CreatXML()

    Call targetFileName
    If sfileName = "False" Then
        Exit Sub
    End If
    
On Error GoTo Open_Error
    
    Dim soutfileName      As String
    Dim WK_Str      As String
    Dim sKeyName As String
    Dim index As Integer
    
    sKeyName = sfileName
    index = InStr(sKeyName, "\")
    While index > 0
        sKeyName = Mid(sKeyName, index + 1)
        index = InStr(sKeyName, "\")
    Wend
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'Open sfileName & ".xml" For Output Shared As #1
    soutfileName = sfileName & ".xml"
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Dim WriteStream As Object
    Set WriteStream = CreateObject("ADODB.Stream") '�ļ������ģʽ
    WriteStream.Type = 2     'adTypeText
    WriteStream.Charset = "UTF-8"
    WriteStream.Open
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    Dim i           As Integer
    Dim sTemp As String
    Dim CellName      As String
    CellName = ActiveSheet.Name
    
    '����������
    sTemp = "'" & upFirstStr(ULCase(sdbname)) & "DBO'"
    
    '����������
    'WriteStream.WriteText "<!-- Need to Copy Into Config XML(mybatis.xml) file -->" & vbCrLf
    'WriteStream.WriteText "<!-- " & CellName & " -->" & vbCrLf
    'WriteStream.WriteText "<typeAlias alias=" & sTemp & " type='org.jfp.business.xxx.example.bean." & sdbname & "DBO'/>" & vbCrLf
    'WriteStream.WriteText ""
    
    '����ͷ�ļ�
    WriteStream.WriteText "<?xml version='1.0' encoding='UTF-8' ?> " & vbCrLf
    WriteStream.WriteText "<!DOCTYPE mapper" & vbCrLf
    WriteStream.WriteText "    PUBLIC '-//mybatis.org//DTD Dao 3.0//EN' " & vbCrLf
    WriteStream.WriteText "    'http://mybatis.org/dtd/mybatis-3-mapper.dtd' >" & vbCrLf
    WriteStream.WriteText "<!-- " & CellName & " -->" & vbCrLf
    WriteStream.WriteText "<!-- ��Ҫ��DAO������ȫ·��һ�� -->" & vbCrLf
    WriteStream.WriteText "<mapper namespace='" & JAVA_PATH & upFirstStr(ULCase(sdbname)) & "." & upFirstStr(ULCase(sdbname)) & "Mapper'>" & vbCrLf
    
    '������ֶ�
    WriteStream.WriteText "    <!-- ���ֶ���Ϣ  -->" & vbCrLf
    WriteStream.WriteText "    <sql id='tableColumns'>" & vbCrLf
     WK_Str = "        "
    For i = dataStartLine To dataEndLine     'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        ElseIf i <> dataStartLine Then
                WK_Str = WK_Str & "," & LCase(fieldValue)
        Else
            WK_Str = WK_Str & LCase(fieldValue)
        End If
    Next i
    
    WriteStream.WriteText WK_Str & vbCrLf
    WriteStream.WriteText "    </sql>" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    
    '��ҳ��ѯ��Ϣ
    WriteStream.WriteText "    <!-- ��ѯ��ҳ������Ϣ  -->" & vbCrLf
    WriteStream.WriteText "    <select id='doSelectPage' parameterType=" & sTemp & " resultType=" & sTemp & ">" & vbCrLf
    WriteStream.WriteText "        SELECT <include refid='tableColumns'/> FROM " & LCase(sdbname) & vbCrLf
    WriteStream.WriteText "        WHERE 1 = 1 " & vbCrLf
    For i = dataStartLine To dataEndLine     'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        End If
        
        '<if test=��state != null��>
        If propertiesValue <> "String" Then
            WriteStream.WriteText "            <if test=' " & fieldULValue & " != null '><!-- " & itemValue & " -->" & vbCrLf
        Else
            WriteStream.WriteText "            <if test=' " & fieldULValue & " != null and " & fieldULValue & " !="""" '><!-- " & itemValue & " -->" & vbCrLf
        End If
            
        '���������ѯ
        If searchValue <> "" Then
            WriteStream.WriteText "                AND INSTR(" & LCase(fieldValue) & " , #{" & fieldULValue & "} ) != 0" & vbCrLf
        Else
            WriteStream.WriteText "                AND " & LCase(fieldValue) & " = #{" & fieldULValue & "}" & vbCrLf
        End If
        
        WriteStream.WriteText "            </if>" & vbCrLf
    Next i
    WriteStream.WriteText "    </select>" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    
    '������Ϣ
    WriteStream.WriteText "    <!-- ����һ������ -->" & vbCrLf
    WriteStream.WriteText "    <insert id='doInsert' parameterType=" & sTemp & ">" & vbCrLf
    WriteStream.WriteText "        INSERT INTO " & LCase(sdbname) & vbCrLf
    WriteStream.WriteText "          ( <include refid='tableColumns'/>) " & vbCrLf
    WriteStream.WriteText "        VALUES " & vbCrLf
     WK_Str = "          ("
    For i = dataStartLine To dataEndLine     'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        ElseIf i <> dataStartLine Then
            WK_Str = WK_Str & "," & "#{" & fieldULValue & "}"
        Else
            WK_Str = WK_Str & " " & "#{" & fieldULValue & "}"
        End If
    Next i
    
    WriteStream.WriteText WK_Str & " )" & vbCrLf
    WriteStream.WriteText "    </insert>" & vbCrLf
    
    '�޸���Ϣ
    WriteStream.WriteText "    <!-- �޸�һ������ -->" & vbCrLf
    WriteStream.WriteText "    <update id='doUpdate' parameterType=" & sTemp & ">" & vbCrLf
    WriteStream.WriteText "        UPDATE " & LCase(sdbname) & " SET update_time = #{uuu} <!-- ����ʱ�䡢������ -->" & vbCrLf
    For i = dataStartLine To dataEndLine    'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        End If
        If LCase(fieldValue) <> "update_time" Then
            '<if test=��state != null��>
            If propertiesValue <> "String" Then
                WriteStream.WriteText "        <if test=' " & fieldULValue & " != null '><!-- " & itemValue & " -->" & vbCrLf
            Else
                WriteStream.WriteText "        <if test=' " & fieldULValue & " != null and " & fieldULValue & " !="""" '><!-- " & itemValue & " -->" & vbCrLf
            End If
            
            WriteStream.WriteText "            ," & LCase(fieldValue) & " = #{" & fieldULValue & "}" & vbCrLf
            WriteStream.WriteText "        </if>" & vbCrLf
        End If
    Next i
    WriteStream.WriteText "        WHERE 1 = 1 " & vbCrLf
    
    For i = dataStartLine To dataEndLine    'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        End If
        If keyValue <> "" Then
            WriteStream.WriteText "            AND " & LCase(fieldValue) & " = #{" & fieldULValue & "}  <!-- " & itemValue & " -->" & vbCrLf
        End If
    Next i
    
    WriteStream.WriteText "    </update>" & vbCrLf
    
     'ɾ����Ϣ
    WriteStream.WriteText "    <!-- �߼�ɾ��һ������ -->" & vbCrLf
    WriteStream.WriteText "    <delete id='toDelete' parameterType=" & sTemp & ">" & vbCrLf
    WriteStream.WriteText "        UPDATE " & LCase(sdbname) & vbCrLf
    WriteStream.WriteText "            SET del_flag = '1', update_time = #{uuu}, updator = #{updator} " & vbCrLf
    WriteStream.WriteText "        WHERE 1 = 1" & vbCrLf
    WriteStream.WriteText "            <if test=' updateTime != null and updateTime !="""" '><!-- ����ʱ�� -->" & vbCrLf
    WriteStream.WriteText "                AND update_time = #{updateTime}" & vbCrLf
    WriteStream.WriteText "            </if>" & vbCrLf
    
    For i = dataStartLine To dataEndLine    'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        End If
        If keyValue <> "" Then
            WriteStream.WriteText "            AND " & LCase(fieldValue) & " = #{" & fieldULValue & "}  <!-- " & itemValue & " -->" & vbCrLf
        End If
    Next i
        
    WriteStream.WriteText "    </delete>" & vbCrLf
    
    'ɾ����Ϣ
    WriteStream.WriteText "    <!-- ����ɾ��һ������ -->" & vbCrLf
    WriteStream.WriteText "    <delete id='doDelete' parameterType=" & sTemp & ">" & vbCrLf
    WriteStream.WriteText "        DELETE FROM " & LCase(sdbname) & vbCrLf
    WriteStream.WriteText "        WHERE 1 = 1" & vbCrLf
    WriteStream.WriteText "            <if test=' updateTime != null and updateTime !="""" '><!-- ����ʱ�� -->" & vbCrLf
    WriteStream.WriteText "                AND update_time = #{updateTime}" & vbCrLf
    WriteStream.WriteText "            </if>" & vbCrLf
    
    For i = dataStartLine To dataEndLine    'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        End If
        
        If keyValue <> "" Then
            WriteStream.WriteText "            AND " & LCase(fieldValue) & " = #{" & fieldULValue & "}  <!-- " & itemValue & " -->" & vbCrLf
        End If
    Next i
        
    WriteStream.WriteText "    </delete>" & vbCrLf
    
    '��ѯ��Ϣ
    WriteStream.WriteText "    <!-- ��ѯһ������  -->" & vbCrLf
    WriteStream.WriteText "    <select id='doRead' parameterType=" & sTemp & " resultType=" & sTemp & ">" & vbCrLf
    WriteStream.WriteText "        SELECT <include refid='tableColumns'/> FROM " & LCase(sdbname) & " WHERE 1 = 1" & vbCrLf
    
    For i = dataStartLine To dataEndLine    'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        End If
        If keyValue <> "" Then
            WriteStream.WriteText "            AND " & LCase(fieldValue) & " = #{" & fieldULValue & "}  <!-- " & itemValue & " -->" & vbCrLf
        End If
    Next i
    
    'WriteStream.WriteText "            <if test=' ddd != null and ddd !="""" '><!-- ������ʱ�� -->" & vbCrLf
    'WriteStream.WriteText "                AND ddd = #{ddd}" & vbCrLf
    'WriteStream.WriteText "            </if>" & vbCrLf
    
    WriteStream.WriteText "    </select>" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    
    '�������
    WriteStream.WriteText "</mapper>" & vbCrLf
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    tempFilename = SelectPath + "temp.zm"
    WriteStream.SaveToFile tempFilename, 2  'adSaveCreateOverWrite
    
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    
    'ȥ��BOMͷ
    Call Utf8WithoutBom(tempFilename, soutfileName)
    
    'ɾ����ʱ�ļ�
    Kill tempFilename
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    If creatAllSheet = False Then
        MsgBox ("�ļ���������")
    End If
    
    Exit Sub
    
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & "���ٴ�����")
    Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
End Sub
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Sub CreatBean()
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    '����ʵ�����
    Call CreatDBO
    '�����¼���Ӧ�������棩
    Call CreatApiController
    'ϵͳҵ���߼�
    Call CreatBusinesslogic
    '����API�ĵ��ӿ�
    Call CreatApiDoc
    'ϵͳҵ���߼�
    Call CreatDao
    '���ݲ���ʵ�壨����
    Call CreatMapper
    '���ݱ����
    Call CreatSQLApi
End Sub

Sub CreatJSP()
    '�����¼���Ӧ�������棩
    Call CreatPageController
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    '�б�һ��ҳ��
    Call CreatJSPListPage
    '��ϸ��Ϣҳ��
    Call CreatJSPInfoPage
End Sub
Sub CreatDBO()

    Call targetFileName
    If sfileName = "False" Then
        Exit Sub
    End If
        
On Error GoTo Open_Error
    
    Dim sKeyName As String
    Dim soutfileName As String
    Dim index As Integer
    
    sKeyName = sfileName
    index = InStr(sKeyName, "\")
    While index > 0
        sKeyName = Mid(sKeyName, index + 1)
        index = InStr(sKeyName, "\")
    Wend
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'Open sfileName & "PVO.java" For Output Shared As #1
    soutfileName = sfileName & "PVO.java"
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Dim WriteStream As Object
    Set WriteStream = CreateObject("ADODB.Stream") '�ļ������ģʽ
    WriteStream.Type = 2     'adTypeText
    WriteStream.Charset = "UTF-8"
    WriteStream.Open
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    'WriteStream.WriteText "import javax.inject.Named;" & vbCrLf
    WriteStream.WriteText " " & vbCrLf
    'WriteStream.WriteText "@Named" & vbCrLf
    WriteStream.WriteText "/**" & vbCrLf
    WriteStream.WriteText " * " & Trim(Cells(1, 31).Value) & "ҳ���������" & vbCrLf
    WriteStream.WriteText " */" & vbCrLf
    WriteStream.WriteText "public class " & upFirstStr(ULCase(sdbname)) & "PVO extends " & upFirstStr(ULCase(sdbname)) & "DBO" & vbCrLf
    
    WriteStream.WriteText "{" & vbCrLf
    WriteStream.WriteText "}" & vbCrLf
    
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    tempFilename = SelectPath + "temp.zm"
    WriteStream.SaveToFile tempFilename, 2  'adSaveCreateOverWrite
    
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    
    'ȥ��BOMͷ
    Call Utf8WithoutBom(tempFilename, soutfileName)
    
    'ɾ����ʱ�ļ�
    Kill tempFilename
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
      
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'Open sfileName & "DBO.java" For Output Shared As #1
    soutfileName = sfileName & "DBO.java"
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'Dim WriteStream As Object
    Set WriteStream = CreateObject("ADODB.Stream") '�ļ������ģʽ
    WriteStream.Type = 2     'adTypeText
    WriteStream.Charset = "UTF-8"
    WriteStream.Open
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    WriteStream.WriteText "import java.sql.Date;" & vbCrLf
    'WriteStream.WriteText "import javax.inject.Named;" & vbCrLf
    WriteStream.WriteText " " & vbCrLf
    'WriteStream.WriteText "@Named" & vbCrLf
    WriteStream.WriteText "/**" & vbCrLf
    WriteStream.WriteText " * " & Trim(Cells(1, 31).Value) & "�־ò����" & vbCrLf
    WriteStream.WriteText " */" & vbCrLf
    WriteStream.WriteText "public class " & upFirstStr(ULCase(sdbname)) & "DBO extends MyDataBaseObjectSupport3" & vbCrLf
    
    WriteStream.WriteText "{" & vbCrLf
    
    Dim WK_Str      As String
    Dim i           As Integer
    Dim strKeyName      As String
    
    Dim i_start           As Integer
    
    i_start = dataStartLine
    Application.ScreenUpdating = False
            
    'field
    For i = i_start To dataEndLine
        WK_Str = ""
        If Not getEntityValue(i) Then
            Exit For
        End If
    
        If (fieldULValue = WK_Stop_Str) Then
            Exit For
        End If
                  
        If (LCase(fieldValue) <> PRI_KEY) Then
            '/**********��Ŀ����**********/
            WriteStream.WriteText "    /** " & vbCrLf
            WriteStream.WriteText "     * " & itemValue & vbCrLf
            WriteStream.WriteText "     */" & vbCrLf
                
            'fieldValue = LCase(fieldValue)
                 
            ''''''''''''''''''''''''''''''''''''''''''�շ�ת��'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
            fieldValue = fieldULValue
            ''''''''''''''''''''''''''''''''''''''''''�շ�ת��'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
            
            'swaggerע��------------------------------------------------------------------------------------------
            'WK_Str = "    @ApiModelProperty(name = """ & (fieldValue) & """, value = """ & (itemValue) & """)"
            'WriteStream.WriteText WK_Str & vbCrLf
            
            '�ֶ�����
            WK_Str = "    private " & (propertiesValue) & " " & (fieldValue) & " = null;"
               
            WriteStream.WriteText WK_Str & vbCrLf
            WriteStream.WriteText " " & vbCrLf
        End If
        
        
    Next i
    
     'public void prepareNumeric() {
    
    WriteStream.WriteText "    /** " & vbCrLf
    WriteStream.WriteText "     * ��ֵ�ͱ�����ʼ��<br>" & vbCrLf
    WriteStream.WriteText "     * ���ڲ��볡��Ĭ�ϵ���" & vbCrLf
    WriteStream.WriteText "     * @see #loadDefauft()" & vbCrLf
    WriteStream.WriteText "     */" & vbCrLf
    WK_Str = "    public void prepareNumeric() {"
    WriteStream.WriteText WK_Str & vbCrLf
    
    For i = i_start To dataEndLine
        WK_Str = ""
        If Not getEntityValue(i) Then
            Exit For
        End If
    
        If (fieldValue = WK_Stop_Str) Then
            Exit For
        End If
    
        If (LCase(fieldValue) <> PRI_KEY) Then
            '/**********��Ŀ����**********/
            fieldValue = fieldULValue ''''''''''''''''''''''''''''''''''''''''''�շ�ת��'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
                
            '���get����
            If (propertiesValue = "Long") Then
                WriteStream.WriteText "       //��ʼ��" & itemValue & vbCrLf
                WK_Str = "       if(this." & fieldValue & " == null)"
                WriteStream.WriteText WK_Str & vbCrLf
                WriteStream.WriteText "            this." & fieldValue & " = 0L;" & vbCrLf
            ElseIf (propertiesValue = "Double") Then
                WriteStream.WriteText "     //��ʼ��" & itemValue & vbCrLf
                WK_Str = "       if(this." & fieldValue & " == null)"
                WriteStream.WriteText WK_Str & vbCrLf
                WriteStream.WriteText "            this." & fieldValue & " = 0D;" & vbCrLf
            ElseIf (propertiesValue = "BigDecimal") Then
                WriteStream.WriteText "     //��ʼ��" & itemValue & vbCrLf
                WK_Str = "       if(this." & fieldValue & " == null)"
                WriteStream.WriteText WK_Str & vbCrLf
                WriteStream.WriteText "            this." & fieldValue & " = BigDecimal.ZERO;" & vbCrLf
            End If
        End If
        
    Next i
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText " " & vbCrLf
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'get
    For i = i_start To dataEndLine
        WK_Str = ""
        If Not getEntityValue(i) Then
            Exit For
        End If
    
        If (fieldULValue = WK_Stop_Str) Then
            Exit For
        End If
    
        If (LCase(fieldValue) <> PRI_KEY) Then
            '/**********��Ŀ����**********/
            WriteStream.WriteText "    /** " & vbCrLf
            WriteStream.WriteText "     * ��ȡ" & itemValue & vbCrLf
            WriteStream.WriteText "     *" & vbCrLf
            WriteStream.WriteText "     * @return " & fieldValue & " " & itemValue & vbCrLf
            WriteStream.WriteText "     */" & vbCrLf
                
            fieldValue = fieldULValue ''''''''''''''''''''''''''''''''''''''''''�շ�ת��'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
                
            '���get����
            WK_Str = "    public " & propertiesValue & " get" & upFirstStr(fieldValue) & "() {"
            WriteStream.WriteText WK_Str & vbCrLf
            'return
            WK_Str = "        return this." & fieldValue & ";"
            WriteStream.WriteText WK_Str & vbCrLf
            WriteStream.WriteText "    }" & vbCrLf
            WriteStream.WriteText " " & vbCrLf
        End If
    Next i
   
    'set
    For i = i_start To dataEndLine
        WK_Str = ""
        If Not getEntityValue(i) Then
            Exit For
        End If
    
        If (fieldULValue = WK_Stop_Str) Then
            Exit For
        End If
    
        If (LCase(fieldValue) <> PRI_KEY) Then
            '/**********��Ŀ����**********/
            WriteStream.WriteText "    /** " & vbCrLf
            WriteStream.WriteText "     * ����" & itemValue & vbCrLf
            WriteStream.WriteText "     *" & vbCrLf
            WriteStream.WriteText "     * @param " & (fieldValue) & " " & itemValue & vbCrLf
            WriteStream.WriteText "     */" & vbCrLf
                
            fieldValue = fieldULValue ''''''''''''''''''''''''''''''''''''''''''�շ�ת��'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
            'fieldValue = LCase(fieldValue)
                
            'String
            WK_Str = "    public void set" & upFirstStr(fieldValue) & "(" & propertiesValue & " " & LCase(fieldValue) & ") {"
            WriteStream.WriteText WK_Str & vbCrLf
            
            'return
            WK_Str = "        this." & (fieldValue) & " = " & LCase(fieldValue) & ";"
            WriteStream.WriteText WK_Str & vbCrLf
                
            'Other
            WriteStream.WriteText "    }" & vbCrLf
            WriteStream.WriteText " " & vbCrLf
        End If
    Next i
    
    WriteStream.WriteText "}" & vbCrLf
    
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    tempFilename = SelectPath + "temp.zm"
    WriteStream.SaveToFile tempFilename, 2  'adSaveCreateOverWrite
    
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    
    'ȥ��BOMͷ
    Call Utf8WithoutBom(tempFilename, soutfileName)
    
    'ɾ����ʱ�ļ�
    Kill tempFilename
    
    If creatAllSheet = False Then
        MsgBox ("�ļ���������")
    End If
    
    Exit Sub
    
Open_Error:
    MsgBox ("Error")
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
End Sub

Sub CreatApiController()
        
On Error GoTo Open_Error
    Dim soutfileName As String
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'Open sfileName & "Controller.java" For Output Shared As #1
    soutfileName = sfileName & "ApiController.java"
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Dim WriteStream As Object
    Set WriteStream = CreateObject("ADODB.Stream") '�ļ������ģʽ
    WriteStream.Type = 2     'adTypeText
    WriteStream.Charset = "UTF-8"
    WriteStream.Open
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    WriteStream.WriteText "import javax.annotation.Resource;" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    
    'ͷ����
    WriteStream.WriteText "" & vbCrLf
    WriteStream.WriteText "/**" & vbCrLf
    WriteStream.WriteText " * " & Trim(Cells(1, 31).Value) & "�ӿ�" & vbCrLf
    WriteStream.WriteText " */" & vbCrLf
    WriteStream.WriteText "@CrossOrigin" & vbCrLf
    WriteStream.WriteText "@RestController" & vbCrLf
    '
    WriteStream.WriteText "@RequestMapping(value = ""/api/1.0/base/" + LCase(ULCase(sdbname)) + """, method = { RequestMethod.POST})" & vbCrLf
    WriteStream.WriteText "public class " & upFirstStr(ULCase(sdbname)) & "ApiController extends MyControllerSupport { //MyTokenCommonSupport" & vbCrLf
    
    '��������
    Dim bizName      As String
    Dim dboName      As String
    bizName = "biz" & upFirstStr(ULCase(sdbname)) & ""
    dboName = "" & upFirstStr(ULCase(sdbname)) & "DBO"
    
    WriteStream.WriteText "    @Resource" & vbCrLf
    WriteStream.WriteText "    protected " & upFirstStr(ULCase(sdbname)) & "Businesslogic " & bizName & ";" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    WriteStream.WriteText "    //private MyModelAndViewSupport model = super.getModelAndView(""" & sdbname & """);" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    
    ''--------------------------------------------------------------------------------------------------
    WriteStream.WriteText "    //һ������ҳ��ѯ��" & vbCrLf
    WriteStream.WriteText "    @RequestMapping(value = ""/list"", method = RequestMethod.POST)" & vbCrLf
    WriteStream.WriteText "    public RESTResultBean<" & dboName & "> doList(" & dboName & " param, PageModel<" & dboName & "> pageModel) throws Exception {" & vbCrLf
    WriteStream.WriteText "        // ���������־" & vbCrLf
    WriteStream.WriteText "        logger.debug(""param=====>>>>"" + param.toString());" & vbCrLf
    WriteStream.WriteText "        // �趨��ѯ����" & vbCrLf
    WriteStream.WriteText "        pageModel.setFormParamBean(param);" & vbCrLf
    WriteStream.WriteText "        // ������¼��ͳ��" & vbCrLf
    WriteStream.WriteText "        //pageModel.setResultCountFlag(true);" & vbCrLf
    WriteStream.WriteText "        // ��ҳ��ѯ" & vbCrLf
    WriteStream.WriteText "        return " & bizName & ".doList(pageModel);" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "    " & vbCrLf
    WriteStream.WriteText "    //��Ϣ����" & vbCrLf
    WriteStream.WriteText "    @RequestMapping(value = ""/info"", method = RequestMethod.POST)" & vbCrLf
    WriteStream.WriteText "    public RESTResultBean<" & dboName & "> doInfo(" & dboName & " param) throws Exception {" & vbCrLf
    WriteStream.WriteText "        // ���������־" & vbCrLf
    WriteStream.WriteText "        logger.debug(""param=====>>>>"" + param.toString());" & vbCrLf
    WriteStream.WriteText "        // ���ݲ�ѯ" & vbCrLf
    WriteStream.WriteText "        return " & bizName & ".doInfo(param);" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "    " & vbCrLf
    WriteStream.WriteText "    //��Ϣ����" & vbCrLf
    WriteStream.WriteText "    @RequestMapping(value = ""/append"", method = RequestMethod.POST)" & vbCrLf
    WriteStream.WriteText "    public RESTResultBean<String> doAppend(" & dboName & " param) throws Exception {" & vbCrLf
    WriteStream.WriteText "        // ���������־" & vbCrLf
    WriteStream.WriteText "        logger.debug(""param=====>>>>"" + param.toString());" & vbCrLf
    WriteStream.WriteText "        // ��Ϣ����" & vbCrLf
    WriteStream.WriteText "        return " & bizName & ".doAppend(param);" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "    " & vbCrLf
    WriteStream.WriteText "    //��Ϣ�༭" & vbCrLf
    WriteStream.WriteText "    @RequestMapping(value = ""/modify"", method = RequestMethod.POST)" & vbCrLf
    WriteStream.WriteText "    public RESTResultBean<String> doModify(" & dboName & " param) throws Exception {" & vbCrLf
    WriteStream.WriteText "        // ���������־" & vbCrLf
    WriteStream.WriteText "        logger.debug(""param=====>>>>"" + param.toString());" & vbCrLf
    WriteStream.WriteText "        // ��Ϣ����" & vbCrLf
    WriteStream.WriteText "        return " & bizName & ".doModify(param);" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "    " & vbCrLf
    WriteStream.WriteText "    //��Ϣɾ��" & vbCrLf
    WriteStream.WriteText "    @RequestMapping(value = ""/discard"", method = RequestMethod.POST)" & vbCrLf
    WriteStream.WriteText "    public RESTResultBean<String> doDiscard(" & dboName & " param) throws Exception {" & vbCrLf
    WriteStream.WriteText "        // ���������־" & vbCrLf
    WriteStream.WriteText "        logger.debug(""param=====>>>>"" + param.toString());" & vbCrLf
    WriteStream.WriteText "        // ��Ϣ����" & vbCrLf
    WriteStream.WriteText "        return " & bizName & ".doDiscard(param);" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    ''--------------------------------------------------------------------------------------------------
    
    WriteStream.WriteText "}" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
        
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    tempFilename = SelectPath + "temp.zm"
    WriteStream.SaveToFile tempFilename, 2  'adSaveCreateOverWrite
    
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    
    'ȥ��BOMͷ
    Call Utf8WithoutBom(tempFilename, soutfileName)
    
    'ɾ����ʱ�ļ�
    Kill tempFilename
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    Exit Sub
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & "���ٴ�����")
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
End Sub

Sub CreatPageController()

    Call targetFileName
    If sfileName = "False" Then
        Exit Sub
    End If
        
On Error GoTo Open_Error
    
    Dim sKeyName As String
    Dim soutfileName As String
    Dim index As Integer
    
    sKeyName = sfileName
    index = InStr(sKeyName, "\")
    While index > 0
        sKeyName = Mid(sKeyName, index + 1)
        index = InStr(sKeyName, "\")
    Wend
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'Open sfileName & "Controller.java" For Output Shared As #1
    soutfileName = sfileName & "PageController.java"
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Dim WriteStream As Object
    Set WriteStream = CreateObject("ADODB.Stream") '�ļ������ģʽ
    WriteStream.Type = 2     'adTypeText
    WriteStream.Charset = "UTF-8"
    WriteStream.Open
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    WriteStream.WriteText "import javax.annotation.Resource;" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    
    'ͷ����
    WriteStream.WriteText "" & vbCrLf
    WriteStream.WriteText "/**" & vbCrLf
    WriteStream.WriteText " * " & Trim(Cells(1, 31).Value) & "ҳ������߼�" & vbCrLf
    WriteStream.WriteText " */" & vbCrLf
    WriteStream.WriteText "@Controller" & vbCrLf
    '
    WriteStream.WriteText "@RequestMapping(value = ""/page/1.0/base/" + LCase(ULCase(sdbname)) + """, method = { RequestMethod.POST})" & vbCrLf
    WriteStream.WriteText "public class " & upFirstStr(ULCase(sdbname)) & "PageController extends MyControllerSupport { //MyTokenCommonSupport" & vbCrLf
    
    '��������
    Dim daoName      As String
    Dim dboName      As String
    Dim pvoName      As String
    daoName = "dao" & upFirstStr(ULCase(sdbname)) & ""
    dboName = "" & upFirstStr(ULCase(sdbname)) & "DBO"
    pvoName = "" & upFirstStr(ULCase(sdbname)) & "PVO"
    
    WriteStream.WriteText "    @Resource" & vbCrLf
    WriteStream.WriteText "    private " & upFirstStr(ULCase(sdbname)) & "Dao " & daoName & ";" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    WriteStream.WriteText "    //private MyModelAndViewSupport model = super.getModelAndView(""" & sdbname & """);" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    
    ''--------------------------------------------------------------------------------------------------
    WriteStream.WriteText "    //һ������ҳ��ѯ��" & vbCrLf
    WriteStream.WriteText "    @RequestMapping(value = ""/list"", method = RequestMethod.POST)" & vbCrLf
    WriteStream.WriteText "    public ModelAndView doList(" & pvoName & " param, PageModel<" & dboName & "> pageModel, RESTResultBean<String> message) throws Exception {" & vbCrLf
    WriteStream.WriteText "        // ���������־" & vbCrLf
    WriteStream.WriteText "        logger.debug(""param=====>>>>"" + param.toString());" & vbCrLf
    WriteStream.WriteText "        // �趨չʾ����" & vbCrLf
    WriteStream.WriteText "        ModelAndView modelAndView = getModelAndView(""/system/" + upFirstStr(ULCase(sdbname)) + "-list"");" & vbCrLf
    WriteStream.WriteText "        pageModel.setFormParamBean(param);" & vbCrLf
    WriteStream.WriteText "        // ������¼��ͳ��" & vbCrLf
    WriteStream.WriteText "        //pageModel.setResultCountFlag(true);" & vbCrLf
    WriteStream.WriteText "        // ��ҳ��ѯ" & vbCrLf
    WriteStream.WriteText "        " & daoName & ".doSelectPage(pageModel);" & vbCrLf
    WriteStream.WriteText "        // �趨����" & vbCrLf
    WriteStream.WriteText "        modelAndView.addObject(""page"", pageModel);" & vbCrLf
    WriteStream.WriteText "        modelAndView.addObject(""param"", param);" & vbCrLf
    WriteStream.WriteText "        modelAndView.addObject(""message"", message);" & vbCrLf
    WriteStream.WriteText "        return modelAndView;" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "    " & vbCrLf
    
    WriteStream.WriteText "    //������ҳ��" & vbCrLf
    WriteStream.WriteText "    @RequestMapping(value = ""/append"", method = RequestMethod.POST)" & vbCrLf
    WriteStream.WriteText "    public ModelAndView doAppend(" & pvoName & " param, PageModel<" & dboName & "> pageModel, RESTResultBean<String> message) throws Exception {" & vbCrLf
    WriteStream.WriteText "        // �趨չʾ����" & vbCrLf
    WriteStream.WriteText "        ModelAndView modelAndView = getModelAndView(""/system/" + upFirstStr(ULCase(sdbname)) + "-item"");" & vbCrLf
    WriteStream.WriteText "        // ҳ��ģʽ�趨" & vbCrLf
    WriteStream.WriteText "        modelAndView.addObject(""mode"", ONE);" & vbCrLf
    WriteStream.WriteText "        return modelAndView;" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "    " & vbCrLf
    
    WriteStream.WriteText "    //�򿪱༭ҳ��" & vbCrLf
    WriteStream.WriteText "    @RequestMapping(value = ""/modify"", method = RequestMethod.POST)" & vbCrLf
    WriteStream.WriteText "    public ModelAndView doModify(" & pvoName & " param, PageModel<" & dboName & "> pageModel, RESTResultBean<String> message) throws Exception {" & vbCrLf
    WriteStream.WriteText "        // ���������־" & vbCrLf
    WriteStream.WriteText "        logger.debug(""param=====>>>>"" + param.toString());" & vbCrLf
    WriteStream.WriteText "        // �趨չʾ����" & vbCrLf
    WriteStream.WriteText "        ModelAndView modelAndView = getModelAndView(""/system/" + upFirstStr(ULCase(sdbname)) + "-item"");" & vbCrLf
    WriteStream.WriteText "        // ҳ��ģʽ�趨" & vbCrLf
    WriteStream.WriteText "        modelAndView.addObject(""mode"", TWO);" & vbCrLf
    WriteStream.WriteText "        // ���ݲ�ѯ" & vbCrLf
    WriteStream.WriteText "        modelAndView.addObject(""data"", " & daoName & ".doRead(param));" & vbCrLf
    WriteStream.WriteText "        return modelAndView;" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "    " & vbCrLf
    
    WriteStream.WriteText "    //ɾ������" & vbCrLf
    WriteStream.WriteText "    @RequestMapping(value = ""/discard"", method = RequestMethod.POST)" & vbCrLf
    WriteStream.WriteText "    public ModelAndView doDiscard(" & pvoName & " param, PageModel<" & dboName & "> pageModel, RESTResultBean<String> message) throws Exception {" & vbCrLf
    WriteStream.WriteText "        // ���������־" & vbCrLf
    WriteStream.WriteText "        logger.debug(""param=====>>>>"" + param.toString());" & vbCrLf
    WriteStream.WriteText "        // ��Ϣɾ��" & vbCrLf
    WriteStream.WriteText "        " & daoName & ".doDelete(param);" & vbCrLf
    WriteStream.WriteText "        message.setResult(ECCodeMessageConstants.MESSAGE_DB_DELETE);" & vbCrLf
    WriteStream.WriteText "        return doList(new " + pvoName + "(), pageModel, message);" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "    " & vbCrLf
    
    WriteStream.WriteText "    //���ݱ���" & vbCrLf
    WriteStream.WriteText "    @RequestMapping(value = ""/save"", method = RequestMethod.POST)" & vbCrLf
    WriteStream.WriteText "    public ModelAndView doSave(String mode, " & pvoName & " param, PageModel<" & dboName & "> pageModel, RESTResultBean<String> message) throws Exception {" & vbCrLf
    WriteStream.WriteText "        // ���������־" & vbCrLf
    WriteStream.WriteText "        logger.debug(""param=====>>>>"" + param.toString());" & vbCrLf
    WriteStream.WriteText "        // �жϲ������" & vbCrLf
    WriteStream.WriteText "        if (ONE.equals(mode)) { //����" & vbCrLf
    WriteStream.WriteText "            " & daoName & ".doInsert(param);" & vbCrLf
    WriteStream.WriteText "            message.setResult(ECCodeMessageConstants.MESSAGE_DB_INSERT);" & vbCrLf
    WriteStream.WriteText "        } else { //�༭" & vbCrLf
    WriteStream.WriteText "            " & daoName & ".doUpdate(param);" & vbCrLf
    WriteStream.WriteText "            message.setResult(ECCodeMessageConstants.MESSAGE_DB_UPDATE);" & vbCrLf
    WriteStream.WriteText "        }" & vbCrLf
    WriteStream.WriteText "        // �趨չʾ����" & vbCrLf
    WriteStream.WriteText "        ModelAndView modelAndView = getModelAndView(""/common/pageform"");" & vbCrLf
    WriteStream.WriteText "        // ҳ����ʾ��Ϣ�趨" & vbCrLf
    WriteStream.WriteText "        modelAndView.addObject(""message"", message);" & vbCrLf
    WriteStream.WriteText "        // Ŀ��չʾҳ��" & vbCrLf
    WriteStream.WriteText "        modelAndView.addObject(""target"", ""/page/1.0/base/" + LCase(ULCase(sdbname)) + "/list"");" & vbCrLf
    WriteStream.WriteText "        return modelAndView;" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "    " & vbCrLf
    ''--------------------------------------------------------------------------------------------------
    
    WriteStream.WriteText "}" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
        
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    tempFilename = SelectPath + "temp.zm"
    WriteStream.SaveToFile tempFilename, 2  'adSaveCreateOverWrite
    
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    
    'ȥ��BOMͷ
    Call Utf8WithoutBom(tempFilename, soutfileName)
    
    'ɾ����ʱ�ļ�
    Kill tempFilename
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    If creatAllSheet = False Then
        MsgBox ("�ļ���������")
    End If
    
    Exit Sub
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & "���ٴ�����")
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
End Sub

Sub CreatJSPInfoPage()

    'spgname = Cells(1, 31).Value
    
On Error GoTo Open_Error
    Dim soutfileName As String
    Dim WK_Str      As String
    Dim i           As Integer
    Dim strKeyName      As String
    Dim i_start           As Integer
    
    Application.ScreenUpdating = False
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'Open sfileName & "Service.java" For Output Shared As #1
    soutfileName = SelectPath + "-item.jsp"
    sdbname = LCase(ULCase(sdbname))
    SYSNAME = "/page/1.0/base/" + sdbname
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Dim WriteStream As Object
    Set WriteStream = CreateObject("ADODB.Stream") '�ļ������ģʽ
    WriteStream.Type = 2     'adTypeText
    WriteStream.Charset = "UTF-8"
    WriteStream.Open
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''value = ""/page/1.0/base/" + LCase(ULCase(sdbname)
    
    '''''''''''''''''''''����Ƭ��''''''''''''''''''''''''''''''
    WriteStream.WriteText "<%@ page contentType=""text/html;charset=UTF-8"" language=""java"" pageEncoding=""UTF-8""%>" & vbCrLf
    WriteStream.WriteText "<%@ taglib uri=""http://java.sun.com/jsp/jstl/core"" prefix=""c""%>" & vbCrLf
    WriteStream.WriteText "<%@ taglib uri=""http://java.sun.com/jsp/jstl/functions"" prefix=""fn""%>" & vbCrLf
    WriteStream.WriteText "<%@ taglib uri=""/WEB-INF/tag/pagetag.tld"" prefix=""p""%>" & vbCrLf
    WriteStream.WriteText "<%@ taglib uri=""/WEB-INF/tag/pageform.tld"" prefix=""pf""%>" & vbCrLf
    WriteStream.WriteText "<html>" & vbCrLf
    WriteStream.WriteText "<head>" & vbCrLf
    WriteStream.WriteText "<meta name=""keywords"" content=""" + spgname + """>" & vbCrLf
    WriteStream.WriteText "<meta name=""description"" content=""ZM version.0.1.0 since." + Format(Now, "yyyymmddHHmmss") + """>" & vbCrLf
    WriteStream.WriteText "<title>" + LCase(ULCase(sdbname)) + "-item</title>" & vbCrLf
    WriteStream.WriteText "<jsp:include page=""/WEB-INF/views/config/include.jsp"" />" & vbCrLf
    WriteStream.WriteText "<jsp:include page=""/WEB-INF/views/config/jquery-validation.jsp"" />" & vbCrLf
    WriteStream.WriteText "</head>" & vbCrLf
    WriteStream.WriteText "<body class=""body"">" & vbCrLf
    WriteStream.WriteText "<pf:blankiframe name=""" + sdbname + "BlankPageForm"" />" & vbCrLf
    WriteStream.WriteText "<form method=""POST"" action=""" + SYSNAME + "/save"" id=""" + sdbname + "PageForm"" target=""" + sdbname + "BlankPageForm"">" & vbCrLf
    WriteStream.WriteText "<pf:token />" & vbCrLf
    WriteStream.WriteText "<pf:mode data=""${mode}"" />" & vbCrLf
    WriteStream.WriteText "<p:pageCurrent />" & vbCrLf
    WriteStream.WriteText "<p:pageLimit />" & vbCrLf
    
    WriteStream.WriteText "<table>" & vbCrLf
    WriteStream.WriteText "<tr>" & vbCrLf
    WriteStream.WriteText "<td>" & vbCrLf
    WriteStream.WriteText "<table class=""tableparam"">" & vbCrLf
    WriteStream.WriteText "<tr class=""trparam"">" & vbCrLf
    WriteStream.WriteText "<td class=""tdbtn""><input type=""button"" class=""btn btn-secondary btnleft"" value=""����"" onclick=""closePageFormView()"" /></td>" & vbCrLf
    WriteStream.WriteText "<p:tdblank />" & vbCrLf
    WriteStream.WriteText "<td class=""tdbtn""><input type=""submit"" class=""btn btn-primary btnright"" value=""����"" /></td>" & vbCrLf
    WriteStream.WriteText "</tr>" & vbCrLf
    WriteStream.WriteText "</table>" & vbCrLf
    WriteStream.WriteText "</td>" & vbCrLf
    WriteStream.WriteText "</tr>" & vbCrLf
    WriteStream.WriteText "<p:trline />" & vbCrLf
    
    
    '''''''''''''''''''''������'''''''''��ʼ'''''''''''''''''''''
    WriteStream.WriteText "<tr>" & vbCrLf
    WriteStream.WriteText "<td>" & vbCrLf
    WriteStream.WriteText "<table class=""table table-hover"">" & vbCrLf
    i_start = dataStartLine
    For i = i_start To dataEndLine
        WK_Str = ""
        If Not getEntityValue(i) Then
            Exit For
        End If
        
        If (fieldULValue = "id") Then
            WriteStream.WriteText "<pf:id data=""${data.id}"" />" & vbCrLf
        Else
            '��ʼд������
            WriteStream.WriteText "<tr>" & vbCrLf
            WriteStream.WriteText "<pf:title tip=""" + itemValue + """ />" & vbCrLf
            WriteStream.WriteText "<pf:tdinput name=""" + fieldULValue + """ tip=""������" + itemValue + """ value=""${data." + fieldULValue + "}"" />" & vbCrLf
            WriteStream.WriteText "<p:tdline width=""20"" />" & vbCrLf
            WriteStream.WriteText "</tr>" & vbCrLf
        
            If (fieldULValue = WK_Stop_Str) Then
                Exit For
            End If
        End If
        
    Next i
    WriteStream.WriteText "</table>" & vbCrLf
    '''''''''''''''''''''������''''''''''����''''''''''''''''''''
    
    WriteStream.WriteText "</td>" & vbCrLf
    WriteStream.WriteText "</tr>" & vbCrLf
    WriteStream.WriteText "</table>" & vbCrLf
    WriteStream.WriteText "</form>" & vbCrLf
    
    
    '''''''''''''''''''''�ײ�Ƭ��''''''''''''''''''''''''''''''
    WriteStream.WriteText "<p:navbar title=""�༭" + spgname + """ navbar=""" + spgname + """ />" & vbCrLf
    WriteStream.WriteText "<script src=""/resources/views/system/" + sdbname + "-page.js""></script>" & vbCrLf
    WriteStream.WriteText "</body>" & vbCrLf
    WriteStream.WriteText "</html>" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
        
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    tempFilename = SelectPath + "temp.zm"
    WriteStream.SaveToFile tempFilename, 2  'adSaveCreateOverWrite
    
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    
    'ȥ��BOMͷ
    Call Utf8WithoutBom(tempFilename, soutfileName)
    
    'ɾ����ʱ�ļ�
    Kill tempFilename
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    Exit Sub
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & "���ٴ�����")
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
End Sub

Sub CreatJSPListPage()

    'spgname = Cells(1, 31).Value
    
On Error GoTo Open_Error
    Dim soutfileName As String
    Dim WK_Str      As String
    Dim i           As Integer
    Dim headerNum       As Integer
    Dim strKeyName      As String
    Dim i_start           As Integer
    
    Application.ScreenUpdating = False
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'Open sfileName & "Service.java" For Output Shared As #1
    soutfileName = SelectPath + "-list.jsp"
    sdbname = LCase(ULCase(sdbname))
    SYSNAME = "/page/1.0/base/" + sdbname
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Dim WriteStream As Object
    Set WriteStream = CreateObject("ADODB.Stream") '�ļ������ģʽ
    WriteStream.Type = 2     'adTypeText
    WriteStream.Charset = "UTF-8"
    WriteStream.Open
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''value = ""/page/1.0/base/" + LCase(ULCase(sdbname)
    
    '''''''''''''''''''''����Ƭ��''''''''''''''''''''''''''''''
    WriteStream.WriteText "<%@ page contentType=""text/html;charset=UTF-8"" language=""java"" pageEncoding=""UTF-8""%>" & vbCrLf
    WriteStream.WriteText "<%@ taglib uri=""http://java.sun.com/jsp/jstl/core"" prefix=""c""%>" & vbCrLf
    WriteStream.WriteText "<%@ taglib uri=""http://java.sun.com/jsp/jstl/functions"" prefix=""fn""%>" & vbCrLf
    WriteStream.WriteText "<%@ taglib uri=""/WEB-INF/tag/pagetag.tld"" prefix=""p""%>" & vbCrLf
    WriteStream.WriteText "<%@ taglib uri=""/WEB-INF/tag/pageform.tld"" prefix=""pf""%>" & vbCrLf
    WriteStream.WriteText "<html>" & vbCrLf
    WriteStream.WriteText "<head>" & vbCrLf
    WriteStream.WriteText "<meta name=""keywords"" content=""" + spgname + """>" & vbCrLf
    WriteStream.WriteText "<meta name=""description"" content=""ZM version.0.1.0 since." + Format(Now, "yyyymmddHHmmss") + """>" & vbCrLf
    WriteStream.WriteText "<title>" + LCase(ULCase(sdbname)) + "-list</title>" & vbCrLf
    WriteStream.WriteText "<jsp:include page=""/WEB-INF/views/config/include.jsp"" />" & vbCrLf
    WriteStream.WriteText "</head>" & vbCrLf
    WriteStream.WriteText "<body class=""body"">" & vbCrLf
    WriteStream.WriteText "<jsp:include page=""/WEB-INF/views/config/popbox.jsp"" />" & vbCrLf
    WriteStream.WriteText "<form method=""POST"" action=""" + SYSNAME + "/list"" id=""" + sdbname + "ListForm"">" & vbCrLf
    WriteStream.WriteText "<pf:token />" & vbCrLf
    WriteStream.WriteText "<p:pageCurrent />" & vbCrLf
    WriteStream.WriteText "<p:pageLimit />" & vbCrLf
    WriteStream.WriteText "<table class=""tableparam form_box"">" & vbCrLf
    WriteStream.WriteText "<tr class=""trparam"">" & vbCrLf
    WriteStream.WriteText "<td class=""tdbtn"">" & vbCrLf
    WriteStream.WriteText "<input type=""button"" onclick=""showPageFormView('" + SYSNAME + "/append')"" class=""btn btn-light btnleft"" value=""���"">" & vbCrLf
    WriteStream.WriteText "</td>" & vbCrLf
    WriteStream.WriteText "<p:tdblank />" & vbCrLf
    
    
    '''''''''''''''''''''��ѯ��������''''''''''''''''''''''''''''''
    i_start = dataStartLine
    For i = i_start To dataEndLine
        WK_Str = ""
        If Not getEntityValue(i) Then
            Exit For
        End If
    
        If (fieldULValue = WK_Stop_Str) Then
            Exit For
        End If
                  
        If paramValue <> "" Then
            '��ʼд������
            WriteStream.WriteText "<pf:tdparam tip=""" + itemValue + """ />" & vbCrLf
            WriteStream.WriteText "<pf:tdparaminput name=""" + fieldULValue + """ tip=""������" + itemValue + """ value=""${param." + fieldULValue + "}"" />" & vbCrLf
        
        End If
        
    Next i
    
    WriteStream.WriteText "<p:tdline />" & vbCrLf
    WriteStream.WriteText "<td class=""tdbtn"">" & vbCrLf
    WriteStream.WriteText "<input type=""submit"" class=""btn btn-primary btnright"" value=""��ѯ"" />" & vbCrLf
    WriteStream.WriteText "</td>" & vbCrLf
    WriteStream.WriteText "</tr>" & vbCrLf
    WriteStream.WriteText "</table>" & vbCrLf
    WriteStream.WriteText "</form>" & vbCrLf
    
    WriteStream.WriteText "<p:page page=""${page}"" url=""" + SYSNAME + "/list"" />" & vbCrLf
    
    '''''''''''''''''''''�б���Ϣ����''''''''''''''''''''''''''''''
    WriteStream.WriteText "<table class=""table table-striped table-hover"">" & vbCrLf
    WriteStream.WriteText "<tr>" & vbCrLf
        
        
    '''''''''''''''''''''��ͷ''''''''''''''''''''''''''''''
    i_start = dataStartLine
    headerNum = 1
    For i = i_start To dataEndLine
        WK_Str = ""
        If Not getEntityValue(i) Then
            Exit For
        End If
                  
        If listItemValue <> "" Then
            WriteStream.WriteText "<th>" + itemValue + "</th>" & vbCrLf
            headerNum = headerNum + 1
        End If
        
    Next i
    
    WriteStream.WriteText "<th>����</th>" & vbCrLf
    WriteStream.WriteText "</tr>" & vbCrLf
    WriteStream.WriteText "<tbody>" & vbCrLf
    
    WriteStream.WriteText "<p:nodata page=""${page}"" size=""" + CStr(headerNum) + """ />" & vbCrLf
    WriteStream.WriteText "<c:forEach var=""item"" items=""${page.pageListData}"">" & vbCrLf
    WriteStream.WriteText "<tr>" & vbCrLf
        
    '''''''''''''''''''''������''''''''''''''''''''''''''''''
    i_start = dataStartLine
    For i = i_start To dataEndLine
        WK_Str = ""
        If Not getEntityValue(i) Then
            Exit For
        End If
                  
        If listItemValue <> "" Then
            If fieldULValue = WK_Del_Flag Then
                WriteStream.WriteText "<td><pf:delflag data=""${item." + fieldULValue + "}"" /></td>" & vbCrLf
            Else
                WriteStream.WriteText "<td>${item." + fieldULValue + "}</td>" & vbCrLf
            End If
        End If
        
    Next i
        
    WriteStream.WriteText "<td style=""width: 160px;"">" & vbCrLf
    WriteStream.WriteText "<input type=""button"" class=""btn btn2 btn-link"" onclick=""showPageFormView('" + SYSNAME + "/modify?id=${item.id}')"" value=""�༭"" />" & vbCrLf
    WriteStream.WriteText "<input type=""button"" class=""btn btn2 btn-link text-dark"" onclick=""showPageForm('" + SYSNAME + "/discard?id=${item.id}&updateTime=${item.updateTime}')"" value=""ɾ��"" />" & vbCrLf
    WriteStream.WriteText "</td>" & vbCrLf
    WriteStream.WriteText "</tr>" & vbCrLf
    WriteStream.WriteText "</c:forEach>" & vbCrLf
    WriteStream.WriteText "</tbody>" & vbCrLf
    WriteStream.WriteText "</table>" & vbCrLf
    
    
    '''''''''''''''''''''�ײ�Ƭ��''''''''''''''''''''''''''''''
    WriteStream.WriteText "<p:message data=""${message}"" />" & vbCrLf
    WriteStream.WriteText "<p:navbar title=""" + spgname + """ navbar=""" + spgname + """ />" & vbCrLf
    WriteStream.WriteText "<script src=""/resources/views/system/" + sdbname + "-list.js""></script>" & vbCrLf
    WriteStream.WriteText "</body>" & vbCrLf
    WriteStream.WriteText "</html>" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
        
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    tempFilename = SelectPath + "temp.zm"
    WriteStream.SaveToFile tempFilename, 2  'adSaveCreateOverWrite
    
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    
    'ȥ��BOMͷ
    Call Utf8WithoutBom(tempFilename, soutfileName)
    
    'ɾ����ʱ�ļ�
    Kill tempFilename
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    Exit Sub
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & "���ٴ�����")
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
End Sub
Sub CreatBusinesslogic()
        
On Error GoTo Open_Error
    Dim soutfileName As String
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'Open sfileName & "Service.java" For Output Shared As #1
    soutfileName = sfileName & "Businesslogic.java"
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Dim WriteStream As Object
    Set WriteStream = CreateObject("ADODB.Stream") '�ļ������ģʽ
    WriteStream.Type = 2     'adTypeText
    WriteStream.Charset = "UTF-8"
    WriteStream.Open
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Dim daoName      As String
    Dim dboName      As String
    daoName = "dao" & upFirstStr(ULCase(sdbname)) & ""
    dboName = "" & upFirstStr(ULCase(sdbname)) & "DBO"
    
    WriteStream.WriteText "import org.springframework.beans.factory.config.ConfigurableBeanFactory;" & vbCrLf
    WriteStream.WriteText "import org.springframework.context.annotation.Scope;" & vbCrLf
    WriteStream.WriteText "import org.springframework.stereotype.Service;" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    WriteStream.WriteText "/**" & vbCrLf
    WriteStream.WriteText " * " & Trim(Cells(1, 31).Value) & "ҵ����" & vbCrLf
    WriteStream.WriteText " */" & vbCrLf
    WriteStream.WriteText "@Component(""" & upFirstStr(ULCase(sdbname)) & "Businesslogic"")" & vbCrLf
    WriteStream.WriteText "@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)" & vbCrLf
    WriteStream.WriteText "public class " & upFirstStr(ULCase(sdbname)) & "Businesslogic extends MyBusinessLogicSupport {" & vbCrLf
    ''--------------------------------------------------------------------------------------------------
    WriteStream.WriteText "    @Resource" & vbCrLf
    WriteStream.WriteText "    private " & upFirstStr(ULCase(sdbname)) & "Dao " & daoName & ";" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    
    WriteStream.WriteText "    //���API��ַ" & vbCrLf
    WriteStream.WriteText "    public String loadApiDocName() throws Exception {" & vbCrLf
    WriteStream.WriteText "        return """ & Trim(Cells(1, 31).Value) & ";/doc/1.0/base/" & LCase(ULCase(sdbname)) & "/all"";" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "    " & vbCrLf
    
    WriteStream.WriteText "    //һ������ҳ��ѯ��" & vbCrLf
    WriteStream.WriteText "    public RESTResultBean<" & dboName & "> doList(PageModel<" & dboName & "> pageModel) {" & vbCrLf
    WriteStream.WriteText "        // ���巵�ؽ������" & vbCrLf
    WriteStream.WriteText "        RESTResultBean<" & dboName & "> result = new RESTResultBean<" & dboName & ">();" & vbCrLf
    WriteStream.WriteText "        // ��ҳ��ѯ" & vbCrLf
    WriteStream.WriteText "        result.setData(" & daoName & ".doSelectPage(pageModel));" & vbCrLf
    WriteStream.WriteText "        // �趨��ʾ��Ϣ" & vbCrLf
    WriteStream.WriteText "        result.setResult(ECCodeMessageConstants.MESSAGE_DB_SELECT);" & vbCrLf
    WriteStream.WriteText "        // ��������־" & vbCrLf
    WriteStream.WriteText "        logger.debug(""result=====>>>>"" + result.toString());" & vbCrLf
    WriteStream.WriteText "        return result;" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "    " & vbCrLf
    WriteStream.WriteText "    //��Ϣ����" & vbCrLf
    WriteStream.WriteText "    public RESTResultBean<" & dboName & "> doInfo(" & dboName & " param) {" & vbCrLf
    WriteStream.WriteText "        // ���巵�ؽ������" & vbCrLf
    WriteStream.WriteText "        RESTResultBean<" & dboName & "> result = new RESTResultBean<" & dboName & ">();" & vbCrLf
    WriteStream.WriteText "        // ��ѯ����" & vbCrLf
    WriteStream.WriteText "        result.setData(" & daoName & ".doRead(param));" & vbCrLf
    WriteStream.WriteText "        // �趨��ʾ��Ϣ" & vbCrLf
    WriteStream.WriteText "        result.setResult(ECCodeMessageConstants.MESSAGE_DB_SELECT);" & vbCrLf
    WriteStream.WriteText "        // ��������־" & vbCrLf
    WriteStream.WriteText "        logger.debug(""result=====>>>>"" + result.toString());" & vbCrLf
    WriteStream.WriteText "        return result;" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "    " & vbCrLf
    WriteStream.WriteText "    //��Ϣ����" & vbCrLf
    WriteStream.WriteText "    public RESTResultBean<String> doAppend(" & dboName & " param) {" & vbCrLf
    WriteStream.WriteText "        // ���巵�ؽ������" & vbCrLf
    WriteStream.WriteText "        RESTResultBean<String> result = new RESTResultBean<String>();" & vbCrLf
    WriteStream.WriteText "        // ��Ϣ����" & vbCrLf
    WriteStream.WriteText "        " & daoName & ".doInsert(param);" & vbCrLf
    WriteStream.WriteText "        // �趨��ʾ��Ϣ" & vbCrLf
    WriteStream.WriteText "        result.setResult(ECCodeMessageConstants.MESSAGE_DB_INSERT);" & vbCrLf
    WriteStream.WriteText "        // ��������־" & vbCrLf
    WriteStream.WriteText "        logger.debug(""result=====>>>>"" + result.toString());" & vbCrLf
    WriteStream.WriteText "        return result;" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "    " & vbCrLf
    WriteStream.WriteText "    //��Ϣ�༭" & vbCrLf
    WriteStream.WriteText "    public RESTResultBean<String> doModify(" & dboName & " param) {" & vbCrLf
    WriteStream.WriteText "        // ���巵�ؽ��" & vbCrLf
    WriteStream.WriteText "        RESTResultBean<String> result = new RESTResultBean<String>();" & vbCrLf
    WriteStream.WriteText "        // ��Ϣ����" & vbCrLf
    WriteStream.WriteText "        " & daoName & ".doUpdate(param);" & vbCrLf
    WriteStream.WriteText "        // �趨��ʾ��Ϣ" & vbCrLf
    WriteStream.WriteText "        result.setResult(ECCodeMessageConstants.MESSAGE_DB_UPDATE);" & vbCrLf
    WriteStream.WriteText "        // ��������־" & vbCrLf
    WriteStream.WriteText "        logger.debug(""result=====>>>>"" + result.toString());" & vbCrLf
    WriteStream.WriteText "        return result;" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "    " & vbCrLf
    WriteStream.WriteText "    //��Ϣɾ��" & vbCrLf
    WriteStream.WriteText "    public RESTResultBean<String> doDiscard(" & dboName & " param) {" & vbCrLf
    WriteStream.WriteText "        // ���巵�ؽ��" & vbCrLf
    WriteStream.WriteText "        RESTResultBean<String> result = new RESTResultBean<String>();" & vbCrLf
    WriteStream.WriteText "        // ���������־" & vbCrLf
    WriteStream.WriteText "        logger.debug(""param=====>>>>"" + param.toString());" & vbCrLf
    WriteStream.WriteText "        // ��Ϣ����" & vbCrLf
    WriteStream.WriteText "        " & daoName & ".doDelete(param);" & vbCrLf
    WriteStream.WriteText "        // �趨��ʾ��Ϣ" & vbCrLf
    WriteStream.WriteText "        result.setResult(ECCodeMessageConstants.MESSAGE_DB_DELETE);" & vbCrLf
    WriteStream.WriteText "        // ��������־" & vbCrLf
    WriteStream.WriteText "        logger.debug(""result=====>>>>"" + result.toString());" & vbCrLf
    WriteStream.WriteText "        return result;" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    
    
    
    WriteStream.WriteText "" & vbCrLf
    ''--------------------------------------------------------------------------------------------------
    WriteStream.WriteText "}" & vbCrLf
        
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    tempFilename = SelectPath + "temp.zm"
    WriteStream.SaveToFile tempFilename, 2  'adSaveCreateOverWrite
    
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    
    'ȥ��BOMͷ
    Call Utf8WithoutBom(tempFilename, soutfileName)
    
    'ɾ����ʱ�ļ�
    Kill tempFilename
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    Exit Sub
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & "���ٴ�����")
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
End Sub

Sub CreatApiDoc()

On Error GoTo Open_Error
    
    Dim soutfileName As String
    Dim i           As Integer
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'Open sfileName & "ApiDoc.java" For Output Shared As #1
    soutfileName = sfileName & "ApiDoc.java"
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Dim WriteStream As Object
    Set WriteStream = CreateObject("ADODB.Stream") '�ļ������ģʽ
    WriteStream.Type = 2     'adTypeText
    WriteStream.Charset = "UTF-8"
    WriteStream.Open
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'ͷ����
    WriteStream.WriteText "" & vbCrLf
        
    WriteStream.WriteText "import com.alibaba.fastjson.JSONArray;" & vbCrLf
    WriteStream.WriteText "import com.alibaba.fastjson.JSONObject;" & vbCrLf
    
    WriteStream.WriteText "" & vbCrLf
    WriteStream.WriteText "/**" & vbCrLf
    WriteStream.WriteText " * " & Trim(Cells(1, 31).Value) & "�ӿ�" & vbCrLf
    WriteStream.WriteText " */" & vbCrLf
    
    WriteStream.WriteText "@CrossOrigin" & vbCrLf
    WriteStream.WriteText "@RestController" & vbCrLf
    '
    WriteStream.WriteText "@RequestMapping(value = ""/doc/1.0/base/" & LCase(ULCase(sdbname)) & """, method = { RequestMethod.GET})" & vbCrLf
    WriteStream.WriteText "public class " & upFirstStr(ULCase(sdbname)) & "ApiDoc extends MyApiDocSupport { " & vbCrLf
    
    '��������
    WriteStream.WriteText "" & vbCrLf
    
    ''--------------------------------------------------------------------------------------------------
    WriteStream.WriteText "    //API�ĵ�����" & vbCrLf
    WriteStream.WriteText "    @RequestMapping(value = ""/all"", method = RequestMethod.GET)" & vbCrLf
    WriteStream.WriteText "    public JSONObject loadApiDocValue() throws Exception {" & vbCrLf


    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    WriteStream.WriteText "        JSONObject all = new JSONObject();" & vbCrLf
    WriteStream.WriteText "        //���������Ϣ" & vbCrLf
    WriteStream.WriteText "        all.put(""name"", """ + Trim(Cells(1, 31).Value) + """);" & vbCrLf
    WriteStream.WriteText "        all.put(""baseurl"", ""/api/1.0/base/" + LCase(ULCase(sdbname)) + """);" & vbCrLf
    WriteStream.WriteText "        //���API�б�" & vbCrLf
    WriteStream.WriteText "        {" & vbCrLf
    WriteStream.WriteText "            JSONArray list = new JSONArray();" & vbCrLf
    WriteStream.WriteText "            list.add(""/list"");" & vbCrLf
    WriteStream.WriteText "            list.add(""/info"");" & vbCrLf
    WriteStream.WriteText "            list.add(""/append"");" & vbCrLf
    WriteStream.WriteText "            list.add(""/modify"");" & vbCrLf
    WriteStream.WriteText "            list.add(""/discard"");" & vbCrLf
    WriteStream.WriteText "            all.put(""apilist"", list);" & vbCrLf
    WriteStream.WriteText "        }" & vbCrLf
    WriteStream.WriteText "        //����ֶ���Ϣ" & vbCrLf
    WriteStream.WriteText "        {" & vbCrLf
    WriteStream.WriteText "            JSONArray list = new JSONArray();" & vbCrLf
    WriteStream.WriteText "            JSONObject item;" & vbCrLf
    'ѭ������
    'set
    Dim i_start           As Integer
    i_start = dataStartLine
    Application.ScreenUpdating = False
    
    For i = i_start To dataEndLine
        If Not getEntityValue(i) Then
            Exit For
        End If
    
        If (fieldULValue = WK_Stop_Str) Then
            Exit For
        End If
    
        If (LCase(fieldValue) <> PRI_KEY) Then
            '/**********��Ŀ����**********/
            WriteStream.WriteText "            {" & vbCrLf
            WriteStream.WriteText "                item = new JSONObject();" & vbCrLf
            WriteStream.WriteText "                item.put(""enname"", """ + fieldULValue + """);" & vbCrLf
            WriteStream.WriteText "                item.put(""cnname"", """ + itemValue + """);" & vbCrLf
            WriteStream.WriteText "                item.put(""datatype"", """ + propertiesValue + """);" & vbCrLf
            WriteStream.WriteText "                item.put(""meno"", """ + menoValue + """);" & vbCrLf
            WriteStream.WriteText "                " & vbCrLf
            WriteStream.WriteText "                list.add(item);" & vbCrLf
            WriteStream.WriteText "            }" & vbCrLf
        End If
    Next i
            
    WriteStream.WriteText "            all.put(""itemlist"", list);" & vbCrLf
    WriteStream.WriteText "        }" & vbCrLf
    WriteStream.WriteText "        " & vbCrLf
    WriteStream.WriteText "        return all;" & vbCrLf
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "    " & vbCrLf
    
    ''--------------------------------------------------------------------------------------------------
    
    WriteStream.WriteText "}" & vbCrLf
        
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    tempFilename = SelectPath + "temp.zm"
    WriteStream.SaveToFile tempFilename, 2  'adSaveCreateOverWrite
    
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    
    'ȥ��BOMͷ
    Call Utf8WithoutBom(tempFilename, soutfileName)
    
    'ɾ����ʱ�ļ�
    Kill tempFilename
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    Exit Sub
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & "���ٴ�����")
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
End Sub

Sub CreatSQLApi()
        
On Error GoTo Open_Error
    Dim soutfileName As String
    Dim i           As Integer
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'Open sfileName & "SQL.java" For Output Shared As #1
    soutfileName = sfileName & "SQL.java"
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Dim WriteStream As Object
    Set WriteStream = CreateObject("ADODB.Stream") '�ļ������ģʽ
    WriteStream.Type = 2     'adTypeText
    WriteStream.Charset = "UTF-8"
    WriteStream.Open
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    WriteStream.WriteText "import org.springframework.beans.factory.config.ConfigurableBeanFactory;" & vbCrLf
    WriteStream.WriteText "import org.springframework.context.annotation.Scope;" & vbCrLf
    WriteStream.WriteText "import org.springframework.stereotype.Service;" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    WriteStream.WriteText "/**" & vbCrLf
    WriteStream.WriteText " * " & Trim(Cells(1, 31).Value) & "�������" & vbCrLf
    WriteStream.WriteText " */" & vbCrLf
    WriteStream.WriteText "@Component(""" & upFirstStr(ULCase(sdbname)) & "SQL"")" & vbCrLf
    WriteStream.WriteText "//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)" & vbCrLf
    WriteStream.WriteText "public class " & upFirstStr(ULCase(sdbname)) & "SQL extends MyDBTableSQLSupport{" & vbCrLf
    'WriteStream.WriteText "    protected Logger logger = LoggerFactory.getLogger(this.getClass());" & vbCrLf
    'WriteStream.WriteText "" & vbCrLf
    
    WriteStream.WriteText "    // ����" & vbCrLf
    WriteStream.WriteText "    public String getTableName() {" & vbCrLf
    WriteStream.WriteText "        return """ & LCase(sdbname) & """;" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    
    
    
    WriteStream.WriteText "    // ���SQL����" & vbCrLf
    WriteStream.WriteText "    public String getSQL() throws Exception {" & vbCrLf
    WriteStream.WriteText "        StringBuilder sqlStringBuilder = new StringBuilder();//��ʱ����" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    WriteStream.WriteText "        sqlStringBuilder.append(""CREATE TABLE " & LCase(sdbname) & """);" & vbCrLf
    WriteStream.WriteText "        sqlStringBuilder.append(""("");" & vbCrLf
    
    'Head .............
    For i = dataStartLine To dataEndLine     '��ʼѭ��
        WK_Str = ""
        If Not getSQLValue(i) Then
            Exit For
        End If
    
        '/**********����ֶ���Ŀ**********/
        'SQLID VARCHAR2(200) DEFAULT 0
        If fieldValue <> "" Then
            If strItem <> "" Then
                strItem = strItem & " , " & fieldValue
                strInsertVal = strInsertVal & " , " & "?"
                strUpdateVal = strUpdateVal & " ; " & fieldValue & " = ?"
            Else
                strItem = fieldValue
                strInsertVal = "?"
                strUpdateVal = fieldValue & " = ?"
            End If
        End If
        
        '�ؼ���
        If keyValue <> "" Then
            If strKeyName <> "" Then
                strKeyName = strKeyName & " , " & fieldValue
                strWhereVal = strWhereVal & " ; " & fieldValue & " = ?"
            Else
                strKeyName = fieldValue
                strWhereVal = fieldValue & " = ?"
            End If
            
            mustValue = "Y"
            Range("AO" & i).Select   '& ":AQ" & i
            ActiveCell.FormulaR1C1 = "Y"
        End If
        
        WK_Str = "    " & LCase(fieldValue)
        
        '����
        If propertiesValue = "DATE" Or propertiesValue = "TIMESTAMP" Then
            WK_Str = WK_Str & " " & "DATETIME"
        ElseIf propertiesValue = "TEXT" Then
            WK_Str = WK_Str & " " & "TEXT"
        ElseIf propertiesValue = "VARCHAR2" Then
            WK_Str = WK_Str & " " & "VARCHAR" & "(" & numberValue & ")"
        Else
            WK_Str = WK_Str & " " & propertiesValue & "(" & numberValue & ")"
        End If
        
        'Ĭ��ֵ
        If defaultValue <> "" Then
            If keyValue <> "" Then
            
            Else
                WK_Str = WK_Str & " DEFAULT '" & defaultValue & "'"
            End If
        End If

        '��
        If mustValue <> "" Then
            If keyValue <> "" Then
                WK_Str = WK_Str & " NOT NULL"   'unsigned NOT NULL AUTO_INCREMENT
            Else
                WK_Str = WK_Str & " NOT NULL"
            End If
        End If

        'MySQL
        WK_Str = WK_Str & " COMMENT " & "'" & itemValue & "'"

        'SQL
        WK_Str = WK_Str & ","
        'WriteStream.WriteText WK_Str & vbCrLf
                
        WriteStream.WriteText "        sqlStringBuilder.append(""" & WK_Str & """);" & vbCrLf
        
    Next i
    
    '����
    WK_Str = "  PRIMARY KEY (" & LCase(strKeyName) & ")"
    WriteStream.WriteText "        sqlStringBuilder.append(""" & WK_Str & """);" & vbCrLf
    
    WriteStream.WriteText "        sqlStringBuilder.append("") COMMENT '" & spgname & "';"");" & vbCrLf
    
    
    WriteStream.WriteText "" & vbCrLf
    WriteStream.WriteText "        return sqlStringBuilder.toString();" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    WriteStream.WriteText "}" & vbCrLf
        
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    tempFilename = SelectPath + "temp.zm"
    WriteStream.SaveToFile tempFilename, 2  'adSaveCreateOverWrite
    
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    
    'ȥ��BOMͷ
    Call Utf8WithoutBom(tempFilename, soutfileName)
    
    'ɾ����ʱ�ļ�
    Kill tempFilename
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    Exit Sub
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & "���ٴ�����")
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
End Sub

Sub CreatDao()
        
On Error GoTo Open_Error
    Dim soutfileName As String
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'Open sfileName & "Service.java" For Output Shared As #1
    soutfileName = sfileName & "Dao.java"
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Dim WriteStream As Object
    Set WriteStream = CreateObject("ADODB.Stream") '�ļ������ģʽ
    WriteStream.Type = 2     'adTypeText
    WriteStream.Charset = "UTF-8"
    WriteStream.Open
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    WriteStream.WriteText "import org.springframework.beans.factory.config.ConfigurableBeanFactory;" & vbCrLf
    WriteStream.WriteText "import org.springframework.context.annotation.Scope;" & vbCrLf
    WriteStream.WriteText "import org.springframework.stereotype.Service;" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    WriteStream.WriteText "/**" & vbCrLf
    WriteStream.WriteText " * " & Trim(Cells(1, 31).Value) & "���ݿ����ʵ��" & vbCrLf
    WriteStream.WriteText " */" & vbCrLf
    WriteStream.WriteText "@Repository(""" & upFirstStr(ULCase(sdbname)) & "Dao"")" & vbCrLf
    WriteStream.WriteText "//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)" & vbCrLf
    WriteStream.WriteText "public class " & upFirstStr(ULCase(sdbname)) & "Dao extends MyDataBaseOperateSupport2<" & upFirstStr(ULCase(sdbname)) & "DBO" & "> {" & vbCrLf
    'WriteStream.WriteText "    protected Logger logger = LoggerFactory.getLogger(this.getClass());" & vbCrLf
    'WriteStream.WriteText "" & vbCrLf
    
    WriteStream.WriteText "    @Resource" & vbCrLf
    WriteStream.WriteText "    private " & upFirstStr(ULCase(sdbname)) & "Mapper mapper" & upFirstStr(ULCase(sdbname)) & ";" & vbCrLf
    
    WriteStream.WriteText "" & vbCrLf
    WriteStream.WriteText "    public " & upFirstStr(ULCase(sdbname)) & "Mapper getMapper(){" & vbCrLf
    WriteStream.WriteText "        return mapper" & upFirstStr(ULCase(sdbname)) & ";" & vbCrLf
    WriteStream.WriteText "    }" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    WriteStream.WriteText "}" & vbCrLf
        
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    tempFilename = SelectPath + "temp.zm"
    WriteStream.SaveToFile tempFilename, 2  'adSaveCreateOverWrite
    
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    
    'ȥ��BOMͷ
    Call Utf8WithoutBom(tempFilename, soutfileName)
    
    'ɾ����ʱ�ļ�
    Kill tempFilename
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    Exit Sub
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & "���ٴ�����")
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
End Sub
Sub CreatMapper()
        
On Error GoTo Open_Error
    Dim soutfileName As String
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    'Open sfileName & "Dao.java" For Output Shared As #1
    soutfileName = sfileName & "Mapper.java"
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Dim WriteStream As Object
    Set WriteStream = CreateObject("ADODB.Stream") '�ļ������ģʽ
    WriteStream.Type = 2     'adTypeText
    WriteStream.Charset = "UTF-8"
    WriteStream.Open
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

    WriteStream.WriteText "import org.apache.ibatis.annotations.Mapper;" & vbCrLf
    WriteStream.WriteText "/**" & vbCrLf
    WriteStream.WriteText " * " & Trim(Cells(1, 31).Value) & "���ݿ�����ӿڣ�Xmlӳ�䣩" & vbCrLf
    WriteStream.WriteText " */" & vbCrLf
    WriteStream.WriteText "@Mapper" & vbCrLf
    WriteStream.WriteText "public interface " & upFirstStr(ULCase(sdbname)) & "Mapper extends ISDatabaseSupport<" & upFirstStr(ULCase(sdbname)) & "DBO" & "> {" & vbCrLf
    WriteStream.WriteText "" & vbCrLf
    WriteStream.WriteText "}" & vbCrLf
        
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    tempFilename = SelectPath + "temp.zm"
    WriteStream.SaveToFile tempFilename, 2  'adSaveCreateOverWrite
    
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    
    'ȥ��BOMͷ
    Call Utf8WithoutBom(tempFilename, soutfileName)
    
    'ɾ����ʱ�ļ�
    Kill tempFilename
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    Exit Sub
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & "���ٴ�����")
    'Close #1
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    WriteStream.Flush
    WriteStream.Close
    Set WriteStream = Nothing
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
End Sub

