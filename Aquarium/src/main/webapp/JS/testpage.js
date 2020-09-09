const storage = window.sessionStorage;
var app = new Vue({
	el: '#app',
	data: {
		shopInventory: {
			fish: "",
			acc: "",
			equip:""
		},
		myInventory:{
			fish: "",
			acc: "",
			equip:""
		},
		status: "",
		token: "",
		result: false,
		shopView: false,
		invenView: false
	},
	methods: {
		shopOpen() {
			this.shopView = !this.shopView;
			if (this.shopInventory.fish=="")
			{this.shop();}
		},
		invenOpen() {
			this.invenView = !this.invenView;
			if (this.myInventory.fish=="")
			{this.inven();}
		},
		logout() {
			storage.setItem("jwt-auth-token", "");
			storage.setItem("login_memId", "");
			this.result = false;
			location.href = "index.html";
		},
		shop() {
			axios.get("api/allfish",{
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}
			}).then(res => {
				this.shopInventory.fish=res.data;
				console.log(this.shopInventory.fish);
				
			}).catch(e => {
				console.log("allfish 오류"+JSON.stringify(e.response || e.message));
			});
			
			axios.get("api/allEquipments",{
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}
			}).then(res => {
				this.shopInventory.equip=res.data
				console.log(this.shopInventory.equip);
				
			}).catch(e => {
				console.log("allEquipments 오류"+JSON.stringify(e.response || e.message));
			});
			
			axios.get("api/allAccessories",{
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}
			}).then(res => {
				this.shopInventory.acc=res.data
				console.log(this.shopInventory.acc);
				
			}).catch(e => {
				console.log("allAccessories 오류"+JSON.stringify(e.response || e.message));
			});
		},
		inven() {
			axios.get("testpage/fish/select",{
				params: { memId: storage.getItem("login_memId")},
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}
			}).then(res => {
				this.myInventory.fish=res.data;
				console.log("inven--fish--"+this.myInventory.fish);
				
			}).catch(e => {
				console.log("myInventory fish 오류"+JSON.stringify(e.response || e.message));
			});
			
			axios.get("testpage/equipment/select",{
				params: { memId: storage.getItem("login_memId")},
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}
			}).then(res => {
				this.myInventory.equip=res.data
				console.log("inven--equip--"+this.myInventory.equip);
				
			}).catch(e => {
				console.log("allEquipments 오류"+JSON.stringify(e.response || e.message));
			});
			
			axios.get("testpage/accessory/select",{
				params: { memId: storage.getItem("login_memId")},
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}
			}).then(res => {
				this.myInventory.acc=res.data
				console.log("inven--acc--"+this.myInventory.acc);
				
			}).catch(e => {
				console.log("allAccessories 오류"+JSON.stringify(e.response || e.message));
			});
		},
		init() {
			if (storage.getItem("jwt-auth-token")) {
				this.message = storage.getItem("login_memId") + "로 로그인 되었습니다";
			} else {
				storage.setItem("jwt-auth-token", "");
			}
		}
	},
	mounted() {
		this.init();
	}
});
