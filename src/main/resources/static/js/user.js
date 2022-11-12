let index = {
	
	init: function(){
		$("#btn-save").on("click", ()=>{		// this를 바인딩 하기 위해서.
			this.save();
		});
	},
	
	save: function(){
		// alert("user의 save함수 호출.");
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		// console.log(data);
		
		// ajax를 사용하여 세 개의 데이터를 JSON으로 변환하고 insert 하는 요청을 할 것이다. 
		// ajax 호출 시 default는 비동기 호출이다.
		// 절차적으로 시행되는데, ajax 코드의 완료와 관계없이 ajax 코드의 아랫 부분 코드가 실행된다. 
		// ajax 통신이 성공하고 서버에서 JSON을 리턴해주면, 자동으로 JavaScript OBJ로 변환된다.
		$.ajax({
			// 회원가입 수행 요청.
			type: "post",
			url: "/blog/api/user",
			data: JSON.stringify(data),		// http 요청 시 body에 들어가는 내용이다. body에 데이터를 넣으면 마임타입을 넣어줘야 한다.(header)
			contentType: "application/json; charset=utf-8",		// 마임타입.
			dataType: "json"						// 응답 결과를 어떤 형식으로 받을 것인가.(응답은 기본적으로 String으로 온다. 그런데 String의 형태가 JSON이고, 여기에 json으로 명시해주면 JS obj로 변환해준다.)
		}).done(function(resp){				// resp에는 return값이 들어온다.
			// 정상이면 실행.
			alert("회원가입이 완료되었습니다.");
			// console.log(resp);
			location.href="/blog";
		}).fail(function(error){
			// 실패면 실행.
			alert(JSON.stringify(error));
		}); 
	}
	
}

index.init();