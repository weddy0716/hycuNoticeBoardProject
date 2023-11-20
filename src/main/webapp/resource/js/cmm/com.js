
//JSP 초기화 함수
function documentReady(container, callback) {
	$(document).ready(function() {
		
		var dom = $(container); //중복안되도록 처리해야함.
		
		//S - dom ID, inputId 유니크처리
		var curGetTime = (new Date()).getTime();
		dom.attr("id", $(dom).attr("id")+"_"+curGetTime);
		$.each($(dom).find("[data-enc]"), function(){
			$(this).attr("id", ($(this).attr("id")||"")+"_"+(++curGetTime));
		});
		//E - dom ID, inputId 유니크처리

		console.log('++++++++++++++++++++++++++++++++++++++++++++++++');
		console.log('----- documentReady begin ' + container);
		console.log('----- '+ container + ' length ' + dom.length);
		console.log(dom);
		console.log('++++++++++++++++++++++++++++++++++++++++++++++++');
		
		/* 콜백 함수 */
		if (callback) callback(dom);
	});
}


function ajaxAction(json, dataType , url , callback){
	$("#loadingBar").show();
	$.ajax(
        {
            url : url,
            data : json,
            dataType : dataType ,               /*html, text, json, xml, script*/
            method : 'post',
            contentType : "application/x-www-form-urlencoded;charset=utf-8;",
            success : function(res){
				console.log("##PSH success");
				$("#loadingBar").hide();
				eval(callback(res));
            },
            error : function(xhr, status, error){
				$("#loadingBar").hide();
                alert(xhr.status);           // 에러코드(404, 500 등)
                alert(xhr.responseText); // html 포맷의 에러 메시지
            }
        }
    );
}

function ajaxAction2(json, dataType , url , callback){
	$("#loadingBar").show();
	$.ajax(
        {
            url : url,
            async: false,
            data : json,
            dataType : dataType ,               /*html, text, json, xml, script*/
            method : 'post',
            contentType : "application/x-www-form-urlencoded;charset=utf-8;",
            success : function(res){
				console.log("##PSH success");
				$("#loadingBar").hide();
				eval(callback(res));
            },
            error : function(xhr, status, error){
				$("#loadingBar").hide();
                alert(xhr.status);           // 에러코드(404, 500 등)
                alert(xhr.responseText); // html 포맷의 에러 메시지
            }
        }
    );
}
	