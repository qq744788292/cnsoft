Attribute VB_Name = "Tools"
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
'���ݿ���JFP��ܴ���Ƭ�����ɹ���
'
'�汾 4.2.1 2017-5-12
'
'���� Spook
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

Const dataStartLine = 7
Const dataEndLine = 100

Const itemNameCol = 3
Const fieldNameCol = 20
Const propertiesCol = 31
Const numberCol = 36
Const keyCol = 39
Const mustCol = 42
Const defaultCol = 46

Const JAVA_PATH = ""


Dim SYSNAME        As String
Dim spgname         As String
Dim sdbname         As String
Dim sfxdate         As String
Dim sver           As String
Dim sclName           As String

Dim itemValue          As String
Dim fieldValue    As String
Dim fieldULValue    As String
Dim numberValue          As String
Dim mustValue          As String
Dim propertiesValue          As String
Dim keyValue          As String
Dim defaultValue      As String

Dim result           As Boolean
    
Public sfileName As String
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
    fieldValue = Trim(Cells(line, fieldNameCol).Value)
    '�շ�ת��
    fieldULValue = ULCase(fieldValue)
    numberValue = Trim(Cells(line, numberCol).Value)
    propertiesValue = UCase(Trim(Cells(line, propertiesCol).Value))
    mustValue = UCase(Trim(Cells(line, mustCol).Value))
    keyValue = UCase(Trim(Cells(line, keyCol).Value))
    defaultValue = Trim(Cells(line, defaultCol).Value)

    If fieldValue = "" Then
        getSQLValue = False
    Else
        fieldValue = UCase(fieldValue)
        getSQLValue = True
    End If
    
End Function

Function getEntityValue(line As Integer) As Boolean
       
    itemValue = Trim(Cells(line, itemNameCol).Value)
    fieldValue = Trim(Cells(line, fieldNameCol).Value)
    '�շ�ת��
    fieldULValue = ULCase(fieldValue)
    numberValue = Trim(Cells(line, numberCol).Value)
    propertiesValue = LCase(Trim(Cells(line, propertiesCol).Value))
    
    '�������Ͷ���
    changePropertiesValue (propertiesValue)
    
    mustValue = UCase(Trim(Cells(line, mustCol).Value))
    keyValue = UCase(Trim(Cells(line, keyCol).Value))
    defaultValue = Trim(Cells(line, defaultCol).Value)

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


Sub targetFileName()
      SYSNAME = Cells(1, 52).Value
    spgname = Cells(1, 31).Value
    sdbname = SYSNAME & Cells(1, 62).Value
    'sver = Cells(4, 78).Value
       
    sfxdate = Format(Date, "YYYY/MM/DD")

Dim sssss As String
sssss = "��ѡ���ļ����·��" 'upFirstStr(ULCase(sdbname))

    '����ļ�����
    sfileName = Application.GetSaveAsFilename(sssss)
    'Application.GetSaveAsFilename(InitialFilename:=sssss & "", fileFilter:=" , *.")
    
    If sfileName = "" Or sfileName = "False" Then
        MsgBox ("��ָ���ļ�����")
         Exit Sub
    End If
    
    Dim filePath As String
    filePath = upFirstStr(ULCase(sdbname))
    
    
    Dim index As Integer
    index = InStrRev(sfileName, "\")
    
    sfileName = Left(sfileName, InStrRev(sfileName, "\")) + filePath
    
    If Dir(sfileName, vbDirectory) = "" Then
        MkDir sfileName
    End If
    
    sfileName = sfileName & "\" & filePath
    
End Sub
''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Sub CreatSQL()

    Call targetFileName
    If sfileName = "False" Then
        Exit Sub
    End If
On Error GoTo Open_Error
    
    Dim sKeyName As String
    Dim index As Integer
    
    sKeyName = sfileName
    index = InStr(sKeyName, "\")
    While index > 0
        sKeyName = Mid(sKeyName, index + 1)
        index = InStr(sKeyName, "\")
    Wend
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Open sfileName & ".sql" For Output Shared As #1
    
    Dim WK_Str      As String
    Dim i           As Integer
    Dim strKeyName      As String
    Dim strKeyComm      As String
    
    Dim strItem          As String
    Dim strUpdateVal     As String
    Dim strInsertVal     As String
    Dim strWhereVal      As String
    
    Application.ScreenUpdating = False
    
    Print #1, "CREATE TABLE " & LCase(sdbname)
    Print #1, "("
    
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
'        Print #1, "    --" & itemValue
        Print #1, WK_Str
        
    Next i
    
    '����
    If strKeyName = "" Then
        strKeyName = "SEQNO"
        strKeyComm = "COMMENT ON COLUMN " & LCase(sdbname) & "." _
                & strKeyName & " IS 'SQL'"
        Print #1, "    SEQNO VARCHAR2(12) NOT NULL,"
    End If
    
    'Print #1, "    --�L�[���"
    WK_Str = "PRIMARY KEY (" & LCase(strKeyName) & ")"
    Print #1, WK_Str
    
    Print #1, ")" & " COMMENT " & "'" & spgname & "'"
    
    Print #1, ";"
    
    Close #1
    
    MsgBox ("�ļ���������")
    
    Exit Sub
    
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & _
        "���ٴ�����")
End Sub


Sub CreatXML()

    Call targetFileName
    If sfileName = "False" Then
        Exit Sub
    End If
    
On Error GoTo Open_Error
    
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
    Open sfileName & ".xml" For Output Shared As #1
    Dim i           As Integer
    Dim sTemp As String
    Dim CellName      As String
    CellName = ActiveSheet.Name
    
    '����������
    sTemp = "'" & upFirstStr(ULCase(sdbname)) & "DBO'"
    
    '����������
    'Print #1, "<!-- Need to Copy Into Config XML(mybatis.xml) file -->"
    'Print #1, "<!-- " & CellName & " -->"
    'Print #1, "<typeAlias alias=" & sTemp & " type='org.jfp.business.xxx.example.bean." & sdbname & "DBO'/>"
    'Print #1, ""
    
    '����ͷ�ļ�
    Print #1, "<?xml version='1.0' encoding='UTF-8' ?> "
    Print #1, "<!DOCTYPE mapper"
    Print #1, "    PUBLIC '-//mybatis.org//DTD Dao 3.0//EN' "
    Print #1, "    'http://mybatis.org/dtd/mybatis-3-mapper.dtd' >"
    Print #1, "<!-- " & CellName & " -->"
    Print #1, "<!-- ��Ҫ��DAO������ȫ·��һ�� -->"
    Print #1, "<mapper namespace='" & JAVA_PATH & upFirstStr(ULCase(sdbname)) & "." & upFirstStr(ULCase(sdbname)) & "Dao'>"
    
    '���ݱ�ӳ��
    'Print #1, "    <!-- ���ݱ�ӳ�� -->"
    'Print #1, "    <resultMap type=" & sTemp & " id='resultMapFromDate'>"
   ' For i = dataStartLine To dataEndLine     'ѭ������
   '     If Not getEntityValue(i) Then
   '         Exit For
   '     End If
  '      '<result property="d0001" column="d0001"/><!-- ��� -->
  '      Print #1, "        <result property=""" & fieldValue & """ column=""" & fieldValue & """/> <!-- " & itemValue & " -->"
  '  Next i
  '  Print #1, "    </resultMap>"
 '   Print #1, ""
    
    '������ֶ�
    Print #1, "    <!-- ���ֶ���Ϣ  -->"
    Print #1, "    <sql id='tableColumns'>"
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
    
    Print #1, WK_Str
    Print #1, "    </sql>"
    Print #1, ""
    
    '��������
    'Print #1, "    <!-- ��������  -->"
    'Print #1, "    <insert id='doBatchInsert' parameterType=" & sTemp & ">"
    'Print #1, "        INSERT INTO " & LCase(sdbname) & " ( <include refid='tableColumns'/> ) VALUES "
    'Print #1, "            <foreach collection='list' item='item' separator=','>"
    
    'WK_Str = "                ("
    'For i = dataStartLine To dataEndLine     'ѭ������
    '    If Not getEntityValue(i) Then
    '        Exit For
    '    ElseIf i <> dataStartLine Then
    '        WK_Str = WK_Str & "," & "#{item." & LCase(fieldValue) & "}"
    '    Else
    '        WK_Str = WK_Str & " " & "#{item." & LCase(fieldValue) & "}"
    '    End If
    'Next i
    'WK_Str = WK_Str & " )"
    
    'Print #1, WK_Str
    'Print #1, "            </foreach>"
    'Print #1, "    </insert>"
     '����ɾ��
    'Print #1, "    <!-- ����ɾ��  -->"
    'Print #1, "    <delete id='doBatchDelete' parameterType=" & sTemp & ">"
    'Print #1, "        DELETE FROM " & LCase(sdbname) & " WHERE puk IN "
    'Print #1, "            <foreach collection='list' item='item' open='(' separator=',' close=')'>"
    'Print #1, "                #{item.puk}"
    'Print #1, "            </foreach>"
    'Print #1, "    </delete>"
    'Print #1, ""
    
    '���������������м�¼
    Print #1, "    <!-- ���������������м�¼ -->"
    Print #1, "    <update id='doUpdateAll' parameterType=" & sTemp & ">"
    Print #1, "        UPDATE " & LCase(sdbname) & " SET update_time = #{uuu} <!-- ����ʱ�䡢������ -->"
    For i = dataStartLine To dataEndLine    'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        End If
        If LCase(fieldValue) <> "update_time" Then
            '<if test=��state != null��>
            Print #1, "        <if test=' " & (fieldULValue) & " != null and " & (fieldULValue) & " !="""" '><!-- " & itemValue & " -->"
            Print #1, "            ," & LCase(fieldValue) & " = #{" & (fieldULValue) & "}"
            Print #1, "        </if>"
        End If
    Next i
    Print #1, "        WHERE 1 = 1 "
    For i = dataStartLine To dataEndLine     'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        End If
        '<if test=��state != null��>
    Print #1, "            <if test=' " & (fieldULValue) & " != null and " & (fieldULValue) & " !="""" '><!-- " & itemValue & " -->"
    Print #1, "                AND " & LCase(fieldValue) & " = #{" & (fieldULValue) & "}"
    Print #1, "            </if>"
    Next i
    Print #1, "    </update>"
    
    '��ҳ��ѯ��Ϣ
    Print #1, "    <!-- ��ѯ��ҳ������Ϣ  -->"
    Print #1, "    <select id='doSelectPage' parameterType=" & sTemp & " resultType=" & sTemp & ">"
    Print #1, "        SELECT <include refid='tableColumns'/> FROM " & LCase(sdbname)
    Print #1, "        WHERE 1 = 1 "
    For i = dataStartLine To dataEndLine     'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        End If
        '<if test=��state != null��>
        Print #1, "        <if test=' " & fieldULValue & " != null and " & fieldULValue & " !="""" '><!-- " & itemValue & " -->"
        Print #1, "            AND " & LCase(fieldValue) & " = #{" & fieldULValue & "}"
        Print #1, "        </if>"
    Next i
    Print #1, "    </select>"
    Print #1, ""
    
    '������Ϣ
    Print #1, "    <!-- ����һ������ -->"
    Print #1, "    <insert id='doInsert' parameterType=" & sTemp & ">"
    Print #1, "        INSERT INTO " & LCase(sdbname)
    Print #1, "          ( <include refid='tableColumns'/>) "
    Print #1, "        VALUES "
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
    
    Print #1, WK_Str & " )"
    Print #1, "    </insert>"
    
    '�޸���Ϣ
    Print #1, "    <!-- �޸�һ������ -->"
    Print #1, "    <update id='doUpdate' parameterType=" & sTemp & ">"
    Print #1, "        UPDATE " & LCase(sdbname) & " SET update_time = #{uuu} <!-- ����ʱ�䡢������ -->"
    For i = dataStartLine To dataEndLine    'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        End If
        If LCase(fieldValue) <> "update_time" Then
            '<if test=��state != null��>
            Print #1, "        <if test=' " & fieldULValue & " != null and " & fieldULValue & " !="""" '><!-- " & itemValue & " -->"
            Print #1, "            ," & LCase(fieldValue) & " = #{" & fieldULValue & "}"
            Print #1, "        </if>"
        End If
    Next i
    Print #1, "        WHERE 1 = 1 "
    
    For i = dataStartLine To dataEndLine    'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        End If
        If keyValue <> "" Then
            '<if test=��state != null��>
            Print #1, "            <if test=' " & fieldULValue & " != null and " & fieldULValue & " !="""" '><!-- " & itemValue & " -->"
            Print #1, "                AND " & LCase(fieldValue) & " = #{" & fieldULValue & "}"
            Print #1, "            </if>"
        End If
    Next i
    
    Print #1, "    </update>"
    
     'ɾ����Ϣ
    Print #1, "    <!-- �߼�ɾ��һ������ -->"
    Print #1, "    <delete id='toDelete' parameterType=" & sTemp & ">"
    Print #1, "        UPDATE " & LCase(sdbname) & " SET del_flag = '1', update_time = #{uuu}, updator = #{updator} WHERE update_time = #{updateTime}"
    
    For i = dataStartLine To dataEndLine    'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        End If
        If keyValue <> "" Then
            '<if test=��state != null��>
            Print #1, "            <if test=' " & fieldULValue & " != null and " & fieldULValue & " !="""" '><!-- " & itemValue & " -->"
            Print #1, "                AND " & LCase(fieldValue) & " = #{" & fieldULValue & "}"
            Print #1, "            </if>"
        End If
    Next i
        
    Print #1, "    </delete>"
    
    'ɾ����Ϣ
    Print #1, "    <!-- ����ɾ��һ������ -->"
    Print #1, "    <delete id='doDelete' parameterType=" & sTemp & ">"
    Print #1, "        DELETE FROM " & LCase(sdbname) & " WHERE update_time = #{updateTime}"
    
    For i = dataStartLine To dataEndLine    'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        End If
        If keyValue <> "" Then
            '<if test=��state != null��>
            Print #1, "            <if test=' " & fieldULValue & " != null and " & fieldULValue & " !="""" '><!-- " & itemValue & " -->"
            Print #1, "                AND " & LCase(fieldValue) & " = #{" & fieldULValue & "}"
            Print #1, "            </if>"
        End If
    Next i
        
    Print #1, "    </delete>"
    
    '��ѯ��Ϣ
    Print #1, "    <!-- ��ѯһ������  -->"
    Print #1, "    <select id='doRead' parameterType=" & sTemp & " resultType=" & sTemp & ">"
    Print #1, "        SELECT <include refid='tableColumns'/> FROM " & LCase(sdbname) & " WHERE 1 = 1"
    
    For i = dataStartLine To dataEndLine    'ѭ������
        If Not getEntityValue(i) Then
            Exit For
        End If
        If keyValue <> "" Then
            '<if test=��state != null��>
            Print #1, "            <if test=' " & fieldULValue & " != null and " & fieldULValue & " !="""" '><!-- " & itemValue & " -->"
            Print #1, "                AND " & LCase(fieldValue) & " = #{" & fieldULValue & "}"
            Print #1, "            </if>"
        End If
    Next i
    
    'Print #1, "            <if test=' ddd != null and ddd !="""" '><!-- ������ʱ�� -->"
    'Print #1, "                AND ddd = #{ddd}"
    'Print #1, "            </if>"
    
    Print #1, "    </select>"
    Print #1, ""
    
    '�������
    Print #1, "</mapper>"
    Close #1
    
    MsgBox ("�ļ���������")
    
    Exit Sub
    
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & _
        "���ٴ�����")
End Sub
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Sub CreatBean()

    Call targetFileName
    If sfileName = "False" Then
        Exit Sub
    End If
        
On Error GoTo Open_Error
    
    Dim sKeyName As String
    Dim index As Integer
    
    sKeyName = sfileName
    index = InStr(sKeyName, "\")
    While index > 0
        sKeyName = Mid(sKeyName, index + 1)
        index = InStr(sKeyName, "\")
    Wend
    
    Open sfileName & "PVO" & ".java" For Output Shared As #1
    
    'Print #1, "import javax.inject.Named;"
    Print #1, " "
    'Print #1, "@Named"
    Print #1, "/** " & Trim(Cells(1, 31).Value) & "*/"
    Print #1, "public class " & upFirstStr(ULCase(sdbname)) & "PVO extends " & upFirstStr(ULCase(sdbname)) & "DBO"
    
    Print #1, "{"
    Print #1, "}"
    
    Close #1
      
    Open sfileName & "DBO" & ".java" For Output Shared As #1
    
    Print #1, "import java.sql.Date;"
    'Print #1, "import javax.inject.Named;"
    Print #1, " "
    'Print #1, "@Named"
    Print #1, "/** " & Trim(Cells(1, 31).Value) & "*/"
    Print #1, "public class " & upFirstStr(ULCase(sdbname)) & "DBO extends MyDataBaseObjectSupport3"
    
    Print #1, "{"
    
    Dim WK_Str      As String
    Dim i           As Integer
    Dim strKeyName      As String
    Dim WK_Stop_Str      As String
    
    Dim i_stop           As Integer
    
    
    WK_Stop_Str = "Meno"
    i_stop = dataStartLine + 1
    Application.ScreenUpdating = False
            
    'field
    For i = i_stop To dataEndLine
        WK_Str = ""
        If Not getEntityValue(i) Then
            Exit For
        End If
    
        If InStr(fieldValue, WK_Stop_Str) > 0 Then
       ' If fieldValue = "meno" Then
            Exit For
        End If
      
        '/**********��Ŀ����**********/
        Print #1, "    /** "
        Print #1, "     * " & itemValue
        Print #1, "     */"
            
        'fieldValue = LCase(fieldValue)
             
        fieldValue = fieldULValue ''''''''''''''''''''''''''''''''''''''''''�շ�ת��'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        
        'String
        WK_Str = "    private " & (propertiesValue) & " " & (fieldValue) & " = null;"
           
        Print #1, WK_Str
        Print #1, " "
    Next i

    'get
    For i = i_stop To dataEndLine
        WK_Str = ""
        If Not getEntityValue(i) Then
            Exit For
        End If
    
        If InStr(fieldValue, WK_Stop_Str) > 0 Then
            Exit For
        End If
    
        '/**********��Ŀ����**********/
        Print #1, "    /** "
        Print #1, "     * ��ȡ" & itemValue
        Print #1, "     *"
        Print #1, "     * @return " & fieldValue & " " & itemValue
        Print #1, "     */"
            
        fieldValue = fieldULValue ''''''''''''''''''''''''''''''''''''''''''�շ�ת��'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
            
        '���get����
        WK_Str = "    public " & propertiesValue & " get" & upFirstStr(fieldValue) & "() {"
        Print #1, WK_Str
        If (propertiesValue = "Long") Then
            WK_Str = "       if(this." & fieldValue & " == null)"
            Print #1, WK_Str
            Print #1, "            return 0L;"
        ElseIf (propertiesValue = "Double") Then
            WK_Str = "       if(this." & fieldValue & " == null)"
            Print #1, WK_Str
            Print #1, "            return 0D;"
        ElseIf (propertiesValue = "BigDecimal") Then
            WK_Str = "       if(this." & fieldValue & " == null)"
            Print #1, WK_Str
            Print #1, "            return BigDecimal.ZERO;"
        End If
        
        'return
        WK_Str = "        return this." & fieldValue & ";"
        Print #1, WK_Str
        Print #1, "    }"
        Print #1, " "
    Next i
   
    'set
    For i = i_stop To dataEndLine
        WK_Str = ""
        If Not getEntityValue(i) Then
            Exit For
        End If
    
        If InStr(fieldValue, WK_Stop_Str) > 0 Then
            Exit For
        End If
    
        '/**********��Ŀ����**********/
        Print #1, "    /** "
        Print #1, "     * ����" & itemValue
        Print #1, "     *"
        Print #1, "     * @param " & (fieldValue) & " " & itemValue
        Print #1, "     */"
            
        fieldValue = fieldULValue ''''''''''''''''''''''''''''''''''''''''''�շ�ת��'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        'fieldValue = LCase(fieldValue)
            
        'String
        WK_Str = "    public void set" & upFirstStr(fieldValue) & "(" & propertiesValue & " " & LCase(fieldValue) & ") {"
        Print #1, WK_Str
        
        'return
        WK_Str = "        this." & (fieldValue) & " = " & LCase(fieldValue) & ";"
        Print #1, WK_Str
            
        'Other
        Print #1, "    }"
        Print #1, " "
    Next i
    
    Print #1, "}"
    
    Close #1
      
    '�����¼���Ӧ�������棩
    Call CreatController
    'ϵͳҵ���߼�
    Call CreatService
    '���ݲ���ʵ�壨����
    Call CreatDao
    MsgBox ("OK")
    Exit Sub
    
Open_Error:
    MsgBox ("Error")
End Sub
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Sub CreatController()
        
On Error GoTo Open_Error
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Open sfileName & "Controller.java" For Output Shared As #1
    
    Print #1, "import javax.annotation.Resource;"
    Print #1, ""
    
    Print #1, "import org.slf4j.Logger;"
    Print #1, "import org.slf4j.LoggerFactory;"
    Print #1, "import org.springframework.stereotype.Controller;"
        
    Print #1, ""
    Print #1, "@Controller"
    Print #1, "/** " & Trim(Cells(1, 31).Value) & "*/"
    Print #1, "public class " & upFirstStr(ULCase(sdbname)) & "Controller extends MyControllerSupport {"
    Print #1, "    protected static final Logger logger = LoggerFactory.getLogger(" & sdbname & "Controller.class);"
    Print #1, "    @Resource"
    Print #1, "    protected " & upFirstStr(ULCase(sdbname)) & "Service " & upFirstStr(ULCase(sdbname)) & "Service_;"
    Print #1, ""
    Print #1, "    public MyModelAndViewSupport getModelAndView(){"
    Print #1, "        return new MyModelAndViewSupport(""" & sdbname & """);"
    Print #1, "    }"
    Print #1, ""
    Print #1, "}"
        
    Close #1
    
    Exit Sub
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & _
        "���ٴ�����")
End Sub
Sub CreatService()
        
On Error GoTo Open_Error
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Open sfileName & "Service.java" For Output Shared As #1
    
    Print #1, "import org.springframework.beans.factory.config.ConfigurableBeanFactory;"
    Print #1, "import org.springframework.context.annotation.Scope;"
    Print #1, "import org.springframework.stereotype.Service;"
    Print #1, "import org.slf4j.Logger;"
    Print #1, "import org.slf4j.LoggerFactory;"
    Print #1, ""
    Print #1, "/** " & Trim(Cells(1, 31).Value) & "*/"
    Print #1, "@Service(""" & upFirstStr(ULCase(sdbname)) & """)"
    Print #1, "@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)"
    Print #1, "public class " & upFirstStr(ULCase(sdbname)) & "Service extends MyDataBaseOperateSupport2<" & upFirstStr(ULCase(sdbname)) & "DBO" & "> {"
    Print #1, "    protected Logger logger = LoggerFactory.getLogger(this.getClass());"
    Print #1, ""
    Print #1, "    public " & upFirstStr(ULCase(sdbname)) & "Dao getDao(){"
    Print #1, "        return getMySqlSession().getMapper(" & upFirstStr(ULCase(sdbname)) & "Dao.class);"
    Print #1, "    }"
    Print #1, ""
    Print #1, "}"
        
    Close #1
    
    Exit Sub
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & _
        "���ٴ�����")
End Sub
Sub CreatDao()
        
On Error GoTo Open_Error
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Open sfileName & "Dao.java" For Output Shared As #1

    Print #1, "/** " & Trim(Cells(1, 31).Value) & "*/"
    Print #1, "public interface " & upFirstStr(ULCase(sdbname)) & "Dao extends IDatabaseSupport<" & upFirstStr(ULCase(sdbname)) & "DBO" & "> {"
    Print #1, ""
    Print #1, "}"
        
    Close #1
    
    Exit Sub
Open_Error:
    MsgBox ("�ļ�����ʧ��" & Chr(13) & _
        "���ٴ�����")
End Sub
