<!DOCTYPE html>
<html>
<head>
    <title>纯JS省市区联动</title>
    <script type="text/javascript" src="address.js"></script>
</head>
<body>
<div>
省：<select id="cmbProvince"></select>
市：<select id="cmbCity"></select>
区：<select id="cmbArea"></select>
</div>
<br /><br />
<div>
省：<select id="Select1"></select>
市：<select id="Select2"></select>
区：<select id="Select3"></select>
<script type="text/javascript">
	addressInit('cmbProvince', 'cmbCity', 'cmbArea', '陕西', '宝鸡市', '金台区');
	addressInit('Select1', 'Select2', 'Select3');
	addressInit('id1', 'id2', 'id3','${}','${}','${}');
</script>
</div>
</body>
</html>
