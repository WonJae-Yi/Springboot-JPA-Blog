let index = {
	init: function(){ 
		$("#btn-save").on("click",()=>{ // function(){} 대신 화살표 함수 ()=>{} 사용 이유는 this를 바인딩하기 위해서
			this.save();
			});		
		$("#btn-login").on("click",()=>{ // function(){} 대신 화살표 함수 ()=>{} 사용 이유는 this를 바인딩하기 위해서
			this.login();
			});		
	},
	
	save: function(){
		//alert('user의 save함수 호출#이하의 단어와 동일한 것을 찾아 그 값을 변수에 바인딩 한다.
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		//console.log(data);
		
		//ajax호출시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 데이타를 json으로 변경하여 insert요청!! 
		$.ajax({
			type:"POST",
			url:"/api/user",
			data: JSON.stringify(data), //hhtp body data
			contentType:"application/json; charset=utf8", //
			dataType:"json" //요청을 서버로해서 응답이 왔을대 기본적으로 모든것이 문자열(생긴게 json이라면) => javascript object로 변환
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.")
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});  
	},
	
	login: function(){
		//alert('user의 save함수 호출#이하의 단어와 동일한 것을 찾아 그 값을 변수에 바인딩 한다.
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		};
		
		//console.log(data);
		
		//ajax호출시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 데이타를 json으로 변경하여 insert요청!! 
		//GET방식은 위험 - 주소에 아이디와 비번이 보인다.
		$.ajax({
			type:"POST",
			url:"/api/user/login",
			data: JSON.stringify(data), //hhtp body data
			contentType:"application/json; charset=utf8", //
			dataType:"json" //요청을 서버로해서 응답이 왔을대 기본적으로 모든것이 문자열(생긴게 json이라면) => javascript object로 변환
		}).done(function(resp){
			alert("로그인이 완료되었습니다.")
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});  
	}
	
}

index.init();

