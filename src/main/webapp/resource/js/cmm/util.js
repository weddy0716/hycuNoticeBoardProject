// format 
var foramt = (function() {
	return {
		/**
		 * 3자리 숫자마 콤마 추가
		 * @param target
		 */
		addComma: function(target) {
			var _target = target;
			if(typeof target == 'number') {
				_target = target.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			}
			return _target;
		},
		
		/**
		 * 콤마제거
		 * @param target
		 */
		replaceComma: function(target) {
			return target.toString().replace(/(,)/g, "");
		},

		/**
		 * target에 len길이 만큼 "0" 추가
		 * @param target
		 * @param len
		 */
		addYoung: function(target, len) {
			return target.toString().padStart(len, "0");
		},
		
		/**
		 * target에 len길이 만큼 문자 추가
		 * @param target
		 * @param len
		 * @param str
		 */
		addStr: function(target, len, str) {
			return target.toString().padStart(len, str);
		},
		
		/**
		 * 영문 대/소문자 변환
		 * @param target
		 * @param gubun (UP, DOWN)
		 */
		strUpdown: function(target, gubun) {
			var _target = target;

			if(target == "") {
				return target;
			} else {
				switch (gubun) {
					case "up": case "UP":
						_target = target.toUpperCase(); // 대문자 변환
						break;
					case "down": case "DOWN":
						_target = target.toLowerCase(); // 소문자 변환
						break;
					default:
						_target = target.toUpperCase(); // 기본값 (대문자 변환)
					break;
				}
			}
			
			return _target;
		},
		
		/**
		 * 종성여부 체크
		 * 
		 */
		isJongSeong: function(target) {
			var idx = [0, 1, 2];
			var _target = new String(target);
			var numStr1 = "013678lmnLMN";
			var numStr2 = "2459aefhijkoqrsuvwxyzAEFHIJKOQRSUVWXYZ";
			
			if(_target == null || _target.length < 1) {
				return idx[0];
			}
			
			var lastChar = _target.charAt(_target.length-1);
			var lastCharCode = _target.charCodeAt(_target.length-1);
			
			if(numStr1.indexOf(lastChar) > -1) {
				return idx[2];
			} else if(numStr2.indexOf(lastChar) > -1) {
				return idx[1];
			}
			
			if(lastCharCode < 0xac00 || lastCharCode > 0xda0c) {
				return idx[0];
			} else {
				var lastJongSeong = (lastCharCode - 0xAC00) % (21*28) % 28;
				return (lastJongSeong == 0) ? idx[1] : idx[2];
			}
		},
		
		appendPostfix: function(txt, postfixList) {
			var isHangul = JUtilRegex.regexCheck("hangul", txt);
			if(!isHangul) {
				return txt;
			}
			
			var jongSeongTxt = this.isJongSeong(txt);
			return txt + postfixList[jongSeongTxt];
		},
		
		/**
		 * 조사 (을/를)
		 */
		ul: function(txt) {
			return this.appendPostfix(txt, ["(을)를", "를", "을"]);
		},
		
		/**
		 * 조사 (이/가)
		 */
		ka: function(txt) {
			return this.appendPostfix(txt, ["(이)가", "가", "이"]);
		},
		
		/**
		 * 조사 (은/는)
		 */
		un: function(txt) {
			return this.appendPostfix(txt, ["(은)는", "는", "은"]);
		},
		
		/**
		 * 조사 (과/와)
		 */
		wa: function(txt) {
			return this.appendPostfix(txt, ["(와)과", "와", "과"]);
		},
		
		/**
		 * xml형태 JSON으로 변환
		 */
		xmlToJson: function(xml) {
		  // Create the return object
		  var obj = {};
		
		  if (xml.nodeType == 1) {
		    // element
		    // do attributes
		    if (xml.attributes.length > 0) {
		      obj["@attributes"] = {};
		      for (var j = 0; j < xml.attributes.length; j++) {
		        var attribute = xml.attributes.item(j);
		        obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
		      }
		    }
		  } else if (xml.nodeType == 3) {
		    // text
		    obj = xml.nodeValue;
		  }
		
		  // do children
		  // If all text nodes inside, get concatenated text from them.
		  var textNodes = [].slice.call(xml.childNodes).filter(function(node) {
		    return node.nodeType === 3;
		  });
		  if (xml.hasChildNodes() && xml.childNodes.length === textNodes.length) {
		    obj = [].slice.call(xml.childNodes).reduce(function(text, node) {
		      return text + node.nodeValue;
		    }, "");
		  } else if (xml.hasChildNodes()) {
		    for (var i = 0; i < xml.childNodes.length; i++) {
		      var item = xml.childNodes.item(i);
		      var nodeName = item.nodeName;
		      if (typeof obj[nodeName] == "undefined") {
		        obj[nodeName] = foramt.xmlToJson(item);
		      } else {
		        if (typeof obj[nodeName].push == "undefined") {
		          var old = obj[nodeName];
		          obj[nodeName] = [];
		          obj[nodeName].push(old);
		        }
		        obj[nodeName].push(foramt.xmlToJson(item));
		      }
		    }
		  }
		  return obj;
		}
	}
})();


// 정규표현식 (입력값 체크)
var regex = (function() {
	return {
		
		/**
		 * 정규표현식 체크 (type에 따른 분기처리)
		 * pattern(type, str)
		 * @param type
		 * @param str
		 * 
		 */
		regexCheck: function(type, txt, info) {
			var isFlag = false;
			
			switch (type) {
				case "hangul": // 한글 체크
					isFlag = this.hangul(txt);
					break;
				case "space": // 공백 체크
					isFlag = this.space(txt);
					break;
				case "num": // 숫자만 체크
					isFlag = this.num(txt);
					break;
				case "email": // 이메일 체크
					isFlag = this.email(txt);
					break;
				case "phone": // 휴대폰번호
					isFlag = this.phone(txt);
					break;
				case "hp": // 일반 전화번호
					isFlag = this.hp(txt);
					break;
				case "id": // 아이디 체크
					isFlag = this.id(txt);
					break;
				case "pw": // 비밀번호 체크
					isFlag = this.pw(txt);
					break;
				case "txtLen": // 입력데이터 체크 min~max 지정
					isFlag = this.txtLen(txt, info);
					break;	
			default:
				isFlag = false;
				break;
			}
			
			return isFlag;
		},
		
		// 한글 체크
		hangul: function(txt) {
			if(txt.match(/[ㄱ-ㅎㅏ-ㅣ가-힣]/)) {
				return true; // 한글
			} else {
				return false; // 한글아님
			}
		},
		
		// 공백 체크
		space: function(txt) {
			if(txt.match(/\s/g)) {
				return true;
			} else {
				return false;
			}
		},
		// 숫자만 체크
		num: function(txt) {
			if(txt.match(/^[0-9]+$/)) {
				return true;
			} else {
				return false;
			}
		},
		// 이메일 체크
		email: function(txt) {
			if(txt.match(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i)) {
				return true;
			} else {
				return false;
			}
		},
		// 휴대폰번호
		phone: function(txt) {
			if(txt.match(/^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/)) {
				return true;
			} else {
				return false;
			}
		},
		// 일반 전화번호
		hp: function(txt) {
			if(txt.match(/^\d{2,3}-\d{3,4}-\d{4}$/)) {
				return true;
			} else {
				return false;
			}
		},
		// 아이디 (4~20자리)
		id: function(txt) {
			if(txt.match(/^[A-Za-z0-9_]{4,20}$/)) {
				return true;
			} else {
				return false;
			}
		},
		// 비밀번호 (영문+숫자+특수문자 포함 8~20자리)
		pw: function(txt) {
			if(txt.match(/^(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,20}$/)) {
				return true;
			} else {
				return false;
			}
		},
		
		// 입력데이터 체크 min~max 지정
		txtLen: function(txt, info) {
			var min = info.min;
			var max = info.max;
			
			if (txt.match(new RegExp(`^[가-힣ㄱ-ㅎa-zA-Z0-9_~!@#$%^&*()-=+]{${min},${max}}$`))) {
				return true;
			} else {
				return false;
			}
		},
		
		// 특수문자 -> 유니코드 변환
		escape: function(txt) {
			var str = txt;
			str = str.replace(/&/g, "&amp;");
			str = str.replace(/</g, "&lt;");
			str = str.replace(/>/g, "&gt;");
			str = str.replace(/"/g, "&quot;");
			str = str.replace(/'/g, "&apos;");
			str = str.replace(/ /g, "&nbsp;");
			return str;
		},
		// 유니코드 -> 특수문자 변환
		unescape: function(txt) {
			var str = txt;
			str = str.replace(/&amp;/g, '&');
			str = str.replace(/&lt;/g, '<');
			str = str.replace(/&gt;/g, '>');
			str = str.replace(/&quot;/g, '\"');
			str = str.replace(/&apos;/g, "\'");
			str = str.replace(/&nbsp;/g, ' ');
			return str;
		}
	}
})();


// 유효성검사
var validation = (function() {
	return {
		/**
		 * 빈값 체크
		 * isEmpty(obj)
		 * @param obj
		 * @return boolean
		 */
		isEmpty: function(obj) {
			if(typeof obj == 'undefined' || obj == null || obj == '') {
				return true;
			} else {
				return false;
			}
		}
	}
})();

// 모달 팝업
var modal = (function() {
	return {
		/**
		 * 모달팝업 세팅
		 * 
		 * setting(target, reset, noClose)
		 * 
		 * @param target		// 대상모달
		 * @param reset         // Y.모달 입력내용 삭제
		 * @param noClose       // Y.모달 바깥영역 클릭시 닫힘방지
		 * @returns setting modal
		 * 
		 */
		setting: function(target, reset, noClose) {
			if(JUtilValid.isEmpty(target)) {
				return
			}
			
			// Y.모달 입력내용 삭제
			if(reset == "Y") {
				this.onClickReset(target);
			}
			
			// Y.모달 바깥영역 클릭시 닫힘방지
			if(noClose == "Y") {
				this.noClose(target);
			}
		},
		
		/**
		 * 모달팝업을 닫을경우 입력내용 지우기
		 * onClickReset(target)
		 * 
		 * @param target
		 * @return modal close
		 * 
		 */
		onClickReset: function(target) {
			$(target).on("hidden.bs.modal", function() {
				$(this).find("form")[0].reset();
			});
		},
		/**
		 * 요청/응답에 따라 모달팝업을 닫을경우 입력내용 지우기
		 * closeReset(target, callback)
		 * 
		 * @param target
		 * @param callback
		 * @returns modal close + callback
		 * 
		 */
		closeReset: function(target, callback) {
			$(target).find("form")[0].reset();
			if(typeof callback == 'function') {
				callback();
			}
		},
		/**
		 * 모달팝업 강제 닫기
		 * closeModal(target, callback)
		 * 
		 * @param target
		 * @param callback
		 * @returns modal close + callback
		 * 
		 */
		close: function(target, callback) {
			$(target).modal("hide");
			if(typeof callback == 'function') {
				callback();
			}
		},
		/**
		 * 모달팝업의 바깥영역 클릭시 닫힘방지
		 * 
		 * setModal(target)
		 * 
		 * @param target
		 * @returns setting modal
		 * 
		 */
		noClose: function(target) {
			$(target).modal({
				backdrop : 'static' // 모달팝업의 바깥부분을 클릭시 닫힘방지
				, keyboard : false // 키보드로 모달팝업 제어 (true. 허용, false. 불가)
			});
		}
	}
})();

// 날짜
var date = (function() {
	return {
		/**
		 * 현재 날짜
		 * currentDate(su); // 기본값 "."
		 */
		currentDate: function(su) {
			if(JUtilValid.isEmpty(su)) {
				su = "-";
			}
			
			var today	= new Date();
			var year	= today.getFullYear();
			var month	= (today.getMonth() + 1).toString().padStart(2, "0");
			var day		= today.getDate().toString().padStart(2, "0");
			return `${year}${su}${month}${su}${day}`;
		},
		
		/**
		 * 현재 날짜 시간
		 * currentDateTime(su); // 기본값 "."
		 */
		currentDateTime: function(su) {
			if(JUtilValid.isEmpty(su)) {
				su = "-";
			}

			var today	= new Date();
			var year	= today.getFullYear();
			var month	= (today.getMonth() + 1).toString().padStart(2, "0");
			var day		= today.getDate().toString().padStart(2, "0");
			var hour	= today.getHours().toString().padStart(2, "0"); 
			var minute	= today.getMinutes().toString().padStart(2, "0"); 
			var second	= today.getSeconds().toString().padStart(2, "0"); 
			return `${year}${su}${month}${su}${day} ${hour}:${minute}:${second}`;
		},
		
		/**
		* 주말/공휴일 체크 (단일체크)
		* holidayDate(date) // yyyy-MM-dd
		*/
		holidayDate: function(date) {
			var availableDay = true; 	// true : 휴가신청가능, false : 휴가신청 불가
			
			//0:일, 1:월, 2:화, 3:수, 4:목, 5:금, 6:토
			var dayOfWeek = new Date(date).getDay();
			if(dayOfWeek == '0' || dayOfWeek == '6'){
		    	availableDay = false;
			}
			return availableDay;
		}
	}
})();

// filter
var filter = (function() {
	return {
		filter: function() {
			$('input[data-filter]').on('input', function() {
				var filter = $(this).data('filter').split(":");
				var value = $(this).val(); // 입력값
				var filterValue = "";
				
				for(var i = 0; i < value.length; i++) {
					var chr = value.charAt(i);
					var chrVal = false;
					
					for(var j = 0; j < filter.length; j++) {
						var pattern = getPattern(filter[j]);
						if(pattern.test(chr)) {
							chrVal = true;
							break;
						}
					}
					
					if(chrVal) {
						filterValue += chr;
					}
				}
				
				$(this).val(filterValue);
			});
		}
	}
})();

// 로그인
var login = (function() {
	return {
		/**
		 * 로그인 여부
		 * isLogin()
		 * 
		 * @returns true. 로그인 / false. 비로그인
		 */
		isLogin: function() {
			var loginYN = sessionStorage.getItem("loginYN");
			return (loginYN == "Y") ? true : false;
			
		},

		/**
		 * 로그아웃
		 * goLogout()
		 * 
		 * @returns 비로그인일 경우 로그아웃
		 */
		goLogout: function() {
			if(!JUtilLogin.isLogin()) {
				// console.log("로그아웃");
				location.href = "/login/logout.act";
			}
		}
	}
})();

// 페이지
var page = (function() {
	return {
		/**
		 * 이전페이지 이동
		 * goPage(page)
		 * 	goPage(-1)	이전페이지 이동
		 * 	goPage(1)	현재페이지 이동
		 * 
		 * @returns 뒤로가기
		 */
		goPage: function(page) {
			if(JUtilValid.isEmpty(page)) {
				page = 1;
			}
			
			// console.log("페이지 이동");
			history.go(page);
		},
		
		/**
		 * 뒤로가기
		 * goBack()
		 * 
		 * @returns 뒤로가기
		 */
		goBack: function() {
			// console.log("뒤로가기");
			history.back();
		},
		
		/**
		 * 뒤로가기 방지
		 * noBack()
		 * 
		 * @returns 뒤로가기
		 */
		noBack: function() {
			// console.log("뒤로가기 방지");
			history.pushState(null, null, location.href);
			window.onpopstate = function(e) {
				history.go(1);
			}
		}
	}
})();

/**
 * alert 확인시 callback 함수 호출
 * MessageAlert(text, callback)
 * 
 * @param text
 * @param callback
 * @returns callback
 * 
 */
function MessageAlert(text, callback) {
	if(!alert(text)) {
		if(typeof callback == 'function') {
			callback();
		}
	}
}

function getPattern(filter) {
	var patterns = {
		'num'	: /[0-9]/,			// 숫자
		'kor'	: /[ㄱ-ㅎㅏ-ㅣ가-힣]/,	// 한글
		'eng'	: /[a-zA-Z]/,		// 영문
		'lower'	: /[a-z]/,			// 영문 (소문자)
		'upper'	: /[A-Z]/,			// 영문 (대문자)
		'space'	: /[\s]/,			// 공백
		'special' : /[!?@#$%^&*():;+-=~{}<>\_\[\]\|\\\"\'\,\.\/\`\₩]/ // 특수문자
	};
	return patterns[filter] || /[^\s\S]/;
}

// 쿠키생성
function setCookie(key, val, day) {
	if(JUtilValid.isEmpty(day)) {
		day = 1; // 유효기간 (기본값 1일)
	}
	
	return $.cookie(key, val, {expires : day});
}

// 쿠키조회
function getCookie(key) {
	return $.cookie(key);
}

// 쿠키삭제 (true. 삭제 / false. 실패)
function delCookie(key) {
	return $.removeCookie(key);
}

var JUtilFormat	= foramt;		// format 형식
var JUtilRegex	= regex;		// 정규표현식
var JUtilValid	= validation;	// 유효성검사
var JUtilModal	= modal;		// 모달 이벤트
var JUtilDate	= date;			// 날짜
var JUtilFilter	= filter;		// 필터
var JUtilLogin	= login;		// 로그인
var JUtilPage	= page;			// 페이지