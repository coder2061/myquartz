/**
 * 校验是否为正整数
 * 
 * @param vals
 * @returns {Boolean}
 */
function checkIsInt(vals) {
	var re = /^[1-9]+[0-9]*]*$/;
	return re.test(vals);
}
/**
 * 特殊字符校验
 * 
 * @param vals
 * @returns {Boolean}
 */
function containSpecial(vals) {
	var containSpecial = RegExp(/[(\ )(\~)(\!)(\@)(\#)(\$)(\%)(\^)(\*)(\()(\))(\-)(\_)(\+)(\=)(\[)(\])(\{)(\})(\|)(\\)(\;)(\:)(\')(\")(\,)(\.)(\/)(\<)(\>)(\?)(\)]+/);
	return (containSpecial.test(vals));
}
/**
 * 特殊字符检测
 * 
 * @param str
 * @returns {Boolean}
 */
function IsSpecialChar(str) {
	var reg_exp = /^[^`#+=?|\\\'"%;<>]{1,}$/;
	if (reg_exp.test(str)) {
		return false;
	} else {
		return true;
	}
}
/**
 * 是否为空
 * 
 * @param val
 * @returns {Boolean}
 */
function isBlank(val) {
	if (val == null || val == "" || val == undefined) {
		return true;
	} else {
		return false;
	}
}
/**
 * 是否不为空
 * 
 * @param val
 * @returns {Boolean}
 */
function notBlank(val) {
	if (val != null && val != "" && val != undefined) {
		return true;
	} else {
		return false;
	}
}
/**
 * 为空取值
 * @param str
 * @param str2
 * @returns
 */
function strOper(str, str2) {
	if (isBlank(str)) {
		return str2;
	} else {
		return str;
	}
}
/**
 * 获得字符串实际长度
 * 
 * @param str
 * @returns {Number}
 */
function getLength(str) {
	var realLength = 0, len = str.length, charCode = -1;
	for (var i = 0; i < len; i++) {
		charCode = str.charCodeAt(i);
		if (charCode >= 0 && charCode <= 128)
			realLength += 1;
		else
			realLength += 2;
	}
	return realLength;
}

/**
 * 前进
 */
function back() {
	history.go(-1);
}
/**
 * 后退
 */
function forward() {
	history.go(1);
}
/**
 * 刷新
 */
function refresh() {
	history.go(0);
}

function test() {
	$.ajax({
		type : "POST",
		url : "${routepath}commodity/setCardPrices",
		data : "cmdID=" + cmdID,
		dataType : "json",
		async : false,
		success : function(data) {
			var cardList = data.cardList;
			var str = "该商品未设置储值会员卡价格!";
			if (cardList.length > 0) {
				str = "<table style='width: 230px;text-align: left;'>";
				$.each(cardList, function(i, items) {
					str += "<tr><td width='70%'>" + items.Name + "</td>"
							+ "<td width='30%'>" + items.Price
							+ "&nbsp;元</td></tr>";
				})
				str += "</table>";
			}
			$("#cardPrices").html(str);
		}
	});
}