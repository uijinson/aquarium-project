const storage = window.sessionStorage;
var app = new Vue({
	el: '#app',
	data: {
		shopInventory: {
			fish: [],
			equip: [],
			acc: []
		},
		memId: "",
		bowlName: "",
		message: "로그인 하세요",
		status: "",
		token: "",
		info: "",
		detailInfo: "",
		result: false,
		shopView: false
	},
	methods: {
		shopOpen() {
			this.shopView = !this.shopView;
			console.log("aa--" + this.shopInventory);
			this.shop()
		},
		shop() {
			axios.get("api/allfish", {
			},{
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}
			}).then(res => {
				console.dir(JSON.stringify(res.data) + "----");
				//토큰 & memId 정보 저장
				this.shopInventory.fish=JSON.stringify(res.data);
				
				console.log(this.shopInventory.fish);

			}).catch(e => {
				this.setInfo("실패", "", JSON.stringify(e.response || e.message));
				console.log("로그인 오류");
			});
			axios.get("api/allEquipments", {
			},{
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}
			}).then(res => {
					console.dir(JSON.stringify(res.data)+"----");
						//토큰 & memId 정보 저장
						this.shopInventory.equip=JSON.stringify(res.data);
				
						console.log(this.shopInventory.equip);

			}).catch(e => {
				this.setInfo("실패", "", JSON.stringify(e.response || e.message));
				console.log("로그인 오류");
			});
			axios.get("api/allAccessories",  {
			},{
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}
			}).then(res => {
					console.dir(JSON.stringify(res.data)+"----");
						//토큰 & memId 정보 저장
						this.shopInventory.acc=JSON.stringify(res.data);
				
						console.log(this.shopInventory.acc);

			}).catch(e => {
				this.setInfo("실패", "", JSON.stringify(e.response || e.message));
				console.log("로그인 오류");
			});
		},
		init() {
			if (storage.getItem("jwt-auth-token")) {
				this.message = storage.getItem("login_memId") + "로 로그인 되었습니다";
			} else {
				storage.setItem("jwt-auth-token", "");
			}
		} //init()
	},
	mounted() {
		this.init();
	}
});

filterSelection("all")

function filterSelection(c) {
	var x, i;
	x = document.getElementsByClassName("column");
	if (c == "all") c = "";
	for (i = 0; i < x.length; i++) {
		w3RemoveClass(x[i], "show");
		if (x[i].className.indexOf(c) > -1) w3AddClass(x[i], "show");
	}
}

function w3AddClass(element, name) {
	var i, arr1, arr2;
	arr1 = element.className.split(" ");
	arr2 = name.split(" ");
	for (i = 0; i < arr2.length; i++) {
		if (arr1.indexOf(arr2[i]) == -1) {
			element.className += " " + arr2[i];
		}
	}
}

function w3RemoveClass(element, name) {
	var i, arr1, arr2;
	arr1 = element.className.split(" ");
	arr2 = name.split(" ");
	for (i = 0; i < arr2.length; i++) {
		while (arr1.indexOf(arr2[i]) > -1) {
			arr1.splice(arr1.indexOf(arr2[i]), 1);
		}
	}
	element.className = arr1.join(" ");
}


// Add active class to the current button (highlight it)
var btnContainer = document.getElementById("myBtnContainer");
var btns = btnContainer.getElementsByClassName("btn");
for (var i = 0; i < btns.length; i++) {
	btns[i].addEventListener("click", function () {
		var current = document.getElementsByClassName("active");
		current[0].className = current[0].className.replace(" active", "");
		this.className += " active";
	});
}