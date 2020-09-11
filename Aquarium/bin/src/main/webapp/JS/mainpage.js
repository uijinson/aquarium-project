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
		money:"",
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
		saveM(){
			console.log("저장저장");
			axios.put("api/money/vilkill",{
				memId: storage.getItem("login_memId")},{
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}}
			).then(res => {
				console.log("inven-money-fish--"+res.data);
				
			}).catch(e => {
				console.log("calculate price fish 오류"+JSON.stringify(e.response || e.message));
			});
		},
		loadM(){
			console.log("머니보기");
			axios.get("api/money/select",{
				params: { memId: storage.getItem("login_memId")},
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}
			}).then(res => {
				this.money=res.data;
				console.log("money----"+JSON.stringify(res.data));
				
			}).catch(e => {
				console.log("save fish 오류"+JSON.stringify(e.response || e.message));
			});
		},
		buyFish(v){
			//물고기 저장
			axios.get("testpage/fish/buyFish",{
				params: { memId: storage.getItem("login_memId"),
				fishId: v.fishId},
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}
			}).then(res => {
				//this.myInventory.fish=res.data;
				console.log("inven--fish--"+JSON.stringify(res.data));
				
			}).catch(e => {
				console.log("save fish 오류"+JSON.stringify(e.response || e.message));
			});
			this.inven();
			axios.put("api/money/fish",{
				memId: storage.getItem("login_memId"),
				fishId: v.fishId,
				flag: 1},{
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}}
			).then(res => {
				//this.myInventory.fish=res.data;
				console.log("inven-money-fish--"+res.data);
				
			}).catch(e => {
				console.log("calculate price fish 오류"+JSON.stringify(e.response || e.message));
			});
			this.loadM();
		},
		buyAcc(v){
			//acc저장
			axios.get("testpage/accessory/buyAcc",{
				params: { memId: storage.getItem("login_memId"),
				accId: v.accId},
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}
			}).then(res => {
				//this.myInventory.fish=res.data;
				console.log("inven--fish--"+JSON.stringify(res.data));
				
			}).catch(e => {
				console.log("save fish 오류"+JSON.stringify(e.response || e.message));
			});
			this.inven();
			axios.put("api/money/acc",{
				memId: storage.getItem("login_memId"),
				accId: v.accId,
				flag: 1},{
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}}
			).then(res => {
				//this.myInventory.fish=res.data;
				console.log("inven-money-fish--"+res.data);
				
			}).catch(e => {
				console.log("calculate price fish 오류"+JSON.stringify(e.response || e.message));
			});
			this.loadM();
		},
		buyEquip(v){
				axios.get("testpage/equipment/buyEquip",{
					params: { memId: storage.getItem("login_memId"),
					equipId: v.equipId},
					headers: {
						"jwt-auth-token": storage.getItem("jwt-auth-token")
					}
				}).then(res => {
					//this.myInventory.fish=res.data;
					console.log("inven--fish--"+JSON.stringify(res.data));
					
				}).catch(e => {
					console.log("save fish 오류"+JSON.stringify(e.response || e.message));
				});
				this.inven();
				axios.put("api/money/equipment",{
					memId: storage.getItem("login_memId"),
					equipId: v.equipId,
					flag: 1},{
					headers: {
						"jwt-auth-token": storage.getItem("jwt-auth-token")
					}}
				).then(res => {
					//this.myInventory.fish=res.data;
					console.log("inven-money-fish--"+res.data);
					
				}).catch(e => {
					console.log("calculate price fish 오류"+JSON.stringify(e.response || e.message));
				});
				this.loadM();
				//equip 저장
		},
		sellFish(v){
			axios.delete("testpage/deleteFish",{params: {
				fishNo: v.fishNo}},{
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}}
			).then(res => {
				//this.myInventory.fish=res.data;
				console.log("inven-money-fish--"+res.data);
				
			}).catch(e => {
				console.log("calculate price fish 오류"+JSON.stringify(e.response || e.message));
			});
			this.inven();


			axios.put("api/money/fish",{
				memId: storage.getItem("login_memId"),
				fishId: v.fishId.fishId,
				flag: 0},{
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}}
			).then(res => {
				//this.myInventory.fish=res.data;
				console.log("inven-money-fish--"+res.data);
				
			}).catch(e => {
				console.log("calculate price fish 오류"+JSON.stringify(e.response || e.message));
			});
			this.loadM();
		},
		sellAcc(v){
			axios.delete("testpage/deleteAcc",{params: {
				accNo: v.accNo}},{
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}}
			).then(res => {
				//this.myInventory.fish=res.data;
				console.log("inven-money-fish--"+res.data);
				
			}).catch(e => {
				console.log("calculate price fish 오류"+JSON.stringify(e.response || e.message));
			});

			this.inven();

			axios.put("api/money/acc",{
				memId: storage.getItem("login_memId"),
				accId: v.accId.accId,
				flag: 0},{
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}}
			).then(res => {
				//this.myInventory.fish=res.data;
				console.log("inven-money-fish--"+res.data);
				
			}).catch(e => {
				console.log("calculate price fish 오류"+JSON.stringify(e.response || e.message));
			});
			this.loadM();
		},
		sellEquip(v){
			axios.delete("testpage/deleteEquip",{params: {
				equipNo: v.equipNo}},{
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}}
			).then(res => {
				//this.myInventory.fish=res.data;
				console.log("inven-money-fish--"+res.data);
				
			}).catch(e => {
				console.log("calculate price fish 오류"+JSON.stringify(e.response || e.message));
			});
			this.inven();

			axios.put("api/money/equipment",{
				memId: storage.getItem("login_memId"),
				equipId: v.equipId.equipId,
				flag: 0},{
				headers: {
					"jwt-auth-token": storage.getItem("jwt-auth-token")
				}}
			).then(res => {
				//this.myInventory.fish=res.data;
				console.log("inven-money-fish--"+res.data);
				
			}).catch(e => {
				console.log("calculate price fish 오류"+JSON.stringify(e.response || e.message));
			});
			this.loadM();
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
		this.loadM();
	}
});
