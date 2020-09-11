window.onload = function(){
    const signInBtn = document.getElementById("signIn");
    const signUpBtn = document.getElementById("signUp");
    const fistForm = document.getElementById("form1");
    const secondForm = document.getElementById("form2");
    const container = document.querySelector(".container");
	const storage = window.sessionStorage;
	
	var app = new Vue({ 
	    el: '#app',
	    data: {
	        memId: "",
	        pw: "",
	        bowlName: "",
	        message: "로그인 하세요",
	        status: "",
	        token: "",
	        info: "",
	        detailInfo: "",
	        result: false,
	        classList: "right-panel-active" 
	    },
	    methods: {
	        signInbnt() {
	            console.log("signinbnt");
	            console.log(this.classList);
	            
	            this.classList= "";
	            console.log(this.classList);
	        },
	        signupbnt() {
	            this.classList= "right-panel-active";
	        },
	        setInfo(status, token, info) {
	            this.status = status;
	            this.token = token;
	            this.info = info;
	            this.result = true;
	        },
	        setDetailInfo(status, token, info, detailInfo) {
	            this.status = status;
	            this.token = token;
	            this.info = info;
	            this.detailInfo = detailInfo;
	        },
	        logout() {
	            storage.setItem("jwt-auth-token", "");
	            storage.setItem("login_memId", "");
	            this.memId = "";
	            this.pw = "";
	            this.message = "로그인해 주세요";
	            this.result = false;
	            this.setDetailInfo("로그아웃 성공", "", "");
	        },
	        getInfo() {
	            axios.post("/api/info", {
	                memId: this.memId,
	                pw: this.pw
	            }, {
	                headers: {
	                    "jwt-auth-token": storage.getItem("jwt-auth-token")
	                }
	            }).then(res => {
	                this.setDetailInfo("정보 조회 성공", storage.getItem("jwt-auth-token"), this.info,
	                    JSON.stringify(res.data));
	            }).catch(e => {
	                this.setDetailInfo("정보 조회 실패", "", e.response.data.msg);
	            });
	        },
	        login() {
	            storage.setItem("jwt-auth-token", "");
	            storage.setItem("login_memId", "");
	            axios.post("/api/logincheck/signin", {
	                memId: this.memId,
	                pw: this.pw
	            }).then(res => {
	                if (res.data.status) {
	                    this.memId = "";
	                    this.pw = "";
	                    this.message = res.data.data.memId + "로 로그인 되었습니다";
	                    console.dir(res.headers["jwt-auth-token"]);
	                    //화면에 정보 출력
	                    this.setInfo("로그인 성공", res.headers["jwt-auth-token"], JSON.stringify(res.data.data));
	                    //토큰 & memId 정보 저장
	                    storage.setItem("jwt-auth-token", res.headers["jwt-auth-token"]);
	                    storage.setItem("login_memId", res.data.data.memId);
	                    location.href = "mainpage.html";
	                } else {
	                    this.setInfo("", "", "");
	                    this.message = "로그인 하세요";
	                    console.log("로그인 실패");
	                    alert("입력 정보를 확인");
	                }
	            }).catch(e => {
	                this.setInfo("실패", "", JSON.stringify(e.response || e.message));
	                console.log("로그인 오류");
	            });
	        },
	        signUp() {
	        axios.post("/api/members", {
	                memId: this.memId,
	                pw: this.pw,
	                bowlName: this.bowlName
	            }).then(res => {
	                if (res.data) {
	                    alert("회원가입 성공");
	                } else {
	                    alert("회원가입 실패"); // 아이디 중복값 검증 메소드 추가 필요
	                }
	            }).catch(e => {
	            });
	        },
	        init() {
	            if (storage.getItem("jwt-auth-token")) {
	                this.message = storage.getItem("login_memId") + "로 로그인 되었습니다";
	            } else {
	                storage.setItem("jwt-auth-token", "");
	            }
	        }, //init()
	    
	    },
	    mounted() {
	        this.init();
	    }
	});
}