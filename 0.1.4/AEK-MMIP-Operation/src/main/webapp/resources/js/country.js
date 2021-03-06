var countryInit = function(_country,_defaultCountry)
{
	var country = document.getElementById(_country);
	function cmbAddOption(cmb, str, obj)
	{
		var option = document.createElement("OPTION");
		cmb.options.add(option);
		option.innerHTML = str;
		option.value = str;
		option.obj = obj;
	}
	function cmbSelect(cmb, str)
	{
		for(var i=0; i<cmb.options.length; i++)
		{
			if(cmb.options[i].value == str)
			{
				cmb.selectedIndex = i;
				return;
			}
		}
	}
	
	for(var i=0; i<countryList.length; i++)
	{
		cmbAddOption(country, countryList[i], countryList[i]);
	}
	cmbSelect(country, _defaultCountry);
}



var countryList = [ "中国", "美国", "加拿大", "俄罗斯", "埃及", "南非", "希腊", "荷兰", "比利时",
		"法国", "西班牙", "意大利", "罗马尼亚", "瑞士", "奥地利", "英国", "丹麦", "瑞典", "挪威", "波兰",
		"秘鲁", "墨西哥", "古巴", "阿根廷", "巴西", "智利", "哥伦比亚", "委内瑞拉", "马来西亚", "澳大利亚",
		"印度尼西亚", "菲律宾", "新西兰", "新加坡", "泰国", "日本", "韩国", "越南", "土耳其", "印度",
		"巴基斯坦", "阿富汗", "斯里兰卡", "缅甸", "伊朗", "摩洛哥", "阿尔及利亚", "突尼斯", "利比亚", "冈比亚",
		"塞内加尔", "毛里塔尼亚", "圣马力诺", "几内亚", "科特迪瓦", "布基拉法索", "尼日尔", "多哥", "贝宁",
		"毛里求斯", "利比里亚", "塞拉利昂", "加纳", "尼日利亚", "乍得", "中非", "喀麦隆", "佛得角", "圣多美",
		"普林西比", "赤道几内亚", "加蓬", "刚果", "扎伊尔", "安哥拉", "几内亚比绍", "阿森松", "塞舌尔", "苏丹",
		"卢旺达", "埃塞俄比亚", "索马里", "吉布提", "肯尼亚", "坦桑尼亚", "乌干达", "布隆迪", "莫桑比克",
		"赞比亚", "马达加斯加", "留尼旺岛", "津巴布韦", "纳米比亚", "马拉维", "莱索托", "博茨瓦纳", "斯威士兰",
		"科摩罗", "圣赫勒拿", "阿鲁巴岛", "法罗群岛", "格陵兰岛", "匈牙利", "南斯拉夫", "德国", "直布罗陀",
		"葡萄牙", "卢森堡", "爱尔兰", "冰岛", "阿尔巴尼亚", "马耳他", "塞浦路斯", "芬兰", "保加利亚", "梵蒂冈",
		"福克兰群岛", "伯利兹", "危地马拉", "萨尔瓦多", "洪都拉斯", "尼加拉瓜", "哥斯达黎加", "巴拿马", "海地",
		"玻利维亚", "圭亚那", "厄瓜多尔", "法属圭亚那", "巴拉圭", "马提尼克", "苏里南", "乌拉圭", "关岛",
		"文莱", "瑙鲁", "汤加", "所罗门群岛", "瓦努阿图", "斐济", "科克群岛", "纽埃岛", "东萨摩亚", "西萨摩亚",
		"基里巴斯", "图瓦卢", "朝鲜", "柬埔寨", "老挝", "孟加拉国", "马尔代夫", "黎巴嫩", "约旦", "叙利亚",
		"伊拉克", "科威特", "沙特阿拉伯", "阿曼", "以色列", "巴林", "卡塔尔", "不丹", "蒙古", "尼泊尔",
		"威克岛", "中途岛", "夏威夷", "维尔京群岛", "波多黎各", "巴哈马", "圣卢西亚", "牙买加", "巴巴多斯",
		"安圭拉岛", "阿拉斯加", "列支敦士登", "科科斯岛", "诺福克岛", "圣诞岛" ]
