
var loadingManager = null;
var RESOURCES_LOADED = false;
var meshes = {};
var models = {
	angerfish: {
		obj: "models/angerfish.obj",
		mtl: "models/angerfish.mtl",
		mesh: null
	},
	orca: {
		obj: "models/orca.obj",
		mtl: "models/orca.mtl",
		mesh: null
	},
	octo: {
		obj: "models/octo.obj",
		mtl: "models/octo.mtl",
		mesh: null
	},
	pirateship: {
		obj: "models/Pirateship.obj",
		mtl: "models/Pirateship.mtl",
		mesh: null
	},
	pineapple: {
		obj: "models/pineapple.obj",
		mtl: "models/pineapple.mtl",
		mesh: null,
		castShadow: false
	},
	uzi: {
		obj: "models/uziGold.obj",
		mtl: "models/uziGold.mtl",
		mesh: null,
		castShadow: false
	},
};
var map = [ 
	[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1,1,1,1,1,1],
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,1], 
	[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1,1,1,1,1,1], 
], mapW = map.length, mapH = map[0].length;

// Semi-constants
var WIDTH = window.innerWidth,
	HEIGHT = window.innerHeight,
	ASPECT = WIDTH / HEIGHT,
	UNITSIZE = 250,
	WALLHEIGHT = UNITSIZE / 3,
	MOVESPEED = 100,
	LOOKSPEED = 0.075,
	BULLETMOVESPEED = MOVESPEED * 5,
	numenemmies = 5,
	PROJECTILEDAMAGE = 20;
// Global vars
var t = THREE, scene, cam, renderer, controls, clock, projector, model, skin;
var runAnim = true, mouse = { x: 0, y: 0 }, kills = 0, health = 100;
var healthCube, lastHealthPickup = 0;
/*
var finder = new PF.AStarFinder({ // Defaults to Manhattan heuristic
	allowDiagonal: true,
}), grid = new PF.Grid(mapW, mapH, map);
*/
var loadingScreen = {
	scene: new t.Scene(),
	camera: new t.PerspectiveCamera(90, ASPECT, 1, 10000),
	box: new t.Mesh(
		new t.BoxGeometry(0.5, 0.5, 0.5),
		new t.MeshBasicMaterial({ color: 0x0000ff })
	)
};
// Initialize and run on document ready
$(document).ready(function () {
	$('body').append('<div id="intro">Click to start</div>');
	$('#intro').css({ width: WIDTH, height: HEIGHT }).one('click', function (e) {
		e.preventDefault();
		$(this).fadeOut();
		init();
		setInterval(drawRadar, 1000);
		animate();
	});

});

// Setup
function init() {
	clock = new t.Clock(); // Used in render() for controls.update()
	projector = new t.Projector(); // Used in bullet projection
	scene = new t.Scene(); // Holds all objects in the canvas
	scene.fog = new t.FogExp2(0xD6F1FF, 0.0005); // color, density

	loadingScreen.box.position.set(0, 0, 5);
	loadingScreen.camera.lookAt(loadingScreen.box.position);
	loadingScreen.scene.add(loadingScreen.box);
	loadingManager = new t.LoadingManager();
	loadingManager.onProgress = function (item, loaded, total) {
		console.log(item, loaded, total);
	};
	loadingManager.onLoad = function () {
		console.log("loaded all resources");
		RESOURCES_LOADED = true;
		onResourcesLoaded();
	};
	// Set up camera
	cam = new t.PerspectiveCamera(60, ASPECT, 1, 10000); // FOV, aspect, near, far
	cam.position.y = UNITSIZE * .2;
	scene.add(cam);


	//

	// Camera moves with mouse, flies around with WASD/arrow keys
	controls = new t.FirstPersonControls(cam);
	controls.movementSpeed = MOVESPEED;
	controls.lookSpeed = LOOKSPEED;
	controls.lookVertical = false; // Temporary solution; play on flat surfaces only
	controls.noFly = true;

	// World objects
	function onResourcesLoaded() {
		meshes["angerfish1"] = models.angerfish.mesh.clone();
		meshes["angerfish2"] = models.angerfish.mesh.clone();
		meshes["orca1"] = models.orca.mesh.clone();
		meshes["orca2"] = models.orca.mesh.clone();
		meshes["pirateship"] = models.pirateship.mesh.clone();
		meshes["pineapple"] = models.pineapple.mesh.clone();
		meshes["octo"] = models.octo.mesh.clone();
		// Reposition individual meshes, then add meshes to scene

		meshes["angerfish1"].position.set(-UNITSIZE, 25, -UNITSIZE);
		meshes["angerfish1"].scale.set(0.5, 0.5, 0.5);
		meshes["angerfish1"].rotation.set(Math.PI / 2, Math.PI, Math.PI / 2);
		scene.add(meshes["angerfish1"]);

		meshes["angerfish2"].position.set(-UNITSIZE * 2, 25, -UNITSIZE);
		meshes["angerfish2"].scale.set(0.5, 0.5, 0.5);
		meshes["angerfish2"].rotation.set(Math.PI / 2, Math.PI, Math.PI / 2);
		scene.add(meshes["angerfish2"]);

		meshes["octo"].position.set(UNITSIZE * 2, 25, -UNITSIZE * 2);
		meshes["octo"].scale.set(1, 1, 1);
		meshes["octo"].rotation.set(Math.PI / 2, Math.PI, Math.PI / 2);
		scene.add(meshes["octo"]);

		meshes["orca1"].position.set(UNITSIZE * 2, 25, UNITSIZE);
		meshes["orca1"].scale.set(1, 1, 1);
		meshes["orca1"].rotation.set(Math.PI / 2, Math.PI, Math.PI * 2);
		scene.add(meshes["orca1"]);

		meshes["orca2"].position.set(UNITSIZE * 2, 25, UNITSIZE * 3);
		meshes["orca2"].scale.set(1, 1, 1);
		meshes["orca2"].rotation.set(Math.PI / 2, Math.PI, Math.PI * 2);
		scene.add(meshes["orca2"]);

		meshes["pirateship"].position.set(UNITSIZE, 50, -UNITSIZE);
		meshes["pirateship"].rotation.set(0, Math.PI, 0); // Rotate it to face the other way.
		scene.add(meshes["pirateship"]);


		meshes["pineapple"].position.set(0, -20, 500);
		meshes["pineapple"].rotation.set(Math.PI / 2, Math.PI, Math.PI / 2);
		meshes["pineapple"].scale.set(1, 1, 1) // Rotate it to face the other way.
		scene.add(meshes["pineapple"]);
		

		// player weapon
		meshes["playerweapon"] = models.uzi.mesh.clone();
		meshes["playerweapon"].position.set(0, 2, 0);
		meshes["playerweapon"].scale.set(10, 10, 10);
		scene.add(meshes["playerweapon"]);

	}
	setupScene(); 
	setupENEMMY();

	// Handle drawing as WebGL (faster than Canvas but less supported)
	renderer = new t.WebGLRenderer();
	renderer.setSize(WIDTH, HEIGHT);

	// Add the canvas to the document
	renderer.domElement.style.backgroundColor = '#D6F1FF'; // easier to see
	document.body.appendChild(renderer.domElement);

	// Track mouse position so we know where to shoot
	document.addEventListener('mousemove', onDocumentMouseMove, false);

	// Shoot on click
	$(document).click(function (e) {
		e.preventDefault;
		if (e.which === 1) { // Left click only
			createBullet();
		}
	});

	// Display HUD
	$('body').append('<canvas id="radar" width="400" height="330"></canvas>');
	$('body').append('<div id="hud"><p>Health: <span id="health">100</span><br />Score: <span id="score">0</span></p></div>');
	
	// Set up "hurt" flash
	$('body').append('<div id="hurt"></div>');
	$('#hurt').css({ width: WIDTH, height: HEIGHT, });
}



// Helper function for browser frames
function animate() {
	if (runAnim) {
		requestAnimationFrame(animate);
	}
	render();
}

// Update and display
function render() {
	var delta = clock.getDelta(), speed = delta * BULLETMOVESPEED;
	var enemmyspeed = delta * MOVESPEED;
	controls.update(delta); // Move camera

	// // Rotate the health cube
	// healthcube.rotation.x += 0.004
	// healthcube.rotation.y += 0.008;
	// // Allow picking it up once per minute
	// if (Date.now() > lastHealthPickup + 60000) {
	// 	if (distance(cam.position.x, cam.position.z, healthcube.position.x, healthcube.position.z) < 15 && health != 100) {
	// 		health = Math.min(health + 50, 100);
	// 		$('#health').html(health);
	// 		lastHealthPickup = Date.now();
	// 	}
	// 	healthcube.material.wireframe = false;
	// }
	// else {
	// 	healthcube.material.wireframe = true;
	// }

	// Update bullets. Walk backwards through the list so we can remove items.
	for (var i = bullets.length - 1; i >= 0; i--) {
		var b = bullets[i], p = b.position, d = b.ray.direction;
		if (checkWallCollision(p)) {
			bullets.splice(i, 1);
			scene.remove(b);
			continue;
		}
		// Collide with esetupENEMMY
		var hit = false;
		for (var j = enemmy.length - 1; j >= 0; j--) {
			var a = enemmy[j];
			var v = a.geometry.vertices[0];
			var c = a.position;
			var x = Math.abs(v.x), z = Math.abs(v.z);
		
			if (p.x < c.x + x && p.x > c.x - x &&
				p.z < c.z + z && p.z > c.z - z &&
				b.owner != a) {
				bullets.splice(i, 1);
				scene.remove(b);
				a.health -= PROJECTILEDAMAGE;
				var color = a.material.color, percent = a.health / 100;
				a.material.color.setRGB(
					percent * color.r,
					percent * color.g,
					percent * color.b
				);
				hit = true;
				break;
			}
		}
		// Bullet hits player
		if (distance(p.x, p.z, cam.position.x, cam.position.z) < 25 && b.owner != cam) {
			$('#hurt').fadeIn(75);
			health -= 10;
			if (health < 0) health = 0;
			val = health < 25 ? '<span style="color: darkRed">' + health + '</span>' : health;
			$('#health').html(val);
			bullets.splice(i, 1);
			scene.remove(b);
			$('#hurt').fadeOut(350);
		}
		if (!hit) {
			b.translateX(speed * d.x);
			//bullets[i].translateY(speed * bullets[i].direction.y);
			b.translateZ(speed * d.z);
		}
	}

	// Update esetupENEMMY.
	for (var i = enemmy.length - 1; i >= 0; i--) {
		var a = enemmy[i];
		if (a.health <= 0) {
			enemmy.splice(i, 1);
			scene.remove(a);
			kills++;
			$('#score').html(kills * 100);
			addesetupENEMMY();
		}
		// Move esetupENEMMY
		var r = Math.random();
		if (r > 0.995) {
			a.lastRandomX = Math.random() * 2 - 1;
			a.lastRandomZ = Math.random() * 2 - 1;
		}
		a.translateX(enemmyspeed * a.lastRandomX);
		a.translateZ(enemmyspeed * a.lastRandomZ);
		var c = getMapSector(a.position);
		if (c.x < 0 || c.x >= mapW || c.y < 0 || c.y >= mapH || checkWallCollision(a.position)) {
			a.translateX(-2 * enemmyspeed * a.lastRandomX);
			a.translateZ(-2 * enemmyspeed * a.lastRandomZ);
			a.lastRandomX = Math.random() * 2 - 1;
			a.lastRandomZ = Math.random() * 2 - 1;
		}
		if (c.x < -1 || c.x > mapW || c.z < -1 || c.z > mapH) {
			enemmy.splice(i, 1);
			scene.remove(a);
			addesetupENEMMY();
		}
		
		var cc = getMapSector(cam.position);
		if (Date.now() > a.lastShot + 750 && distance(c.x, c.z, cc.x, cc.z) < 2) {
			createBullet(a);
			a.lastShot = Date.now();
		}
	}

	renderer.render(scene, cam); // Repenemmynt

	// Death
	if (health <= 0) {
		runAnim = false;
		$(renderer.domElement).fadeOut();
		$('#radar, #hud, #credits').fadeOut();
		$('#intro').fadeIn();
		$('#intro').html('정신차려...');
		$('#intro').one('click', function () {
			location = location;
		
		});
	}
}

// Set up the objects in the world
function setupScene() {
	var UNITSIZE = 250, units = mapW;

	// Geometry: floor
	var floor = new t.Mesh(
		new t.PlaneGeometry(units * UNITSIZE, units * UNITSIZE, 10, 10),
		new t.MeshPhongMaterial({ color: 0xb55a3c })
	);

	floor.rotation.x -= Math.PI / 2;
	floor.receiveShadow = true;

	scene.add(floor);

	const loader = new t.CubeTextureLoader();
	const texture = loader.load([
		'models/dark-s_px.jpg',
		'models/dark-s_nx.jpg',
		'models/dark-s_py.jpg',
		'models/dark-s_ny.jpg',
		'models/dark-s_pz.jpg',
		'models/dark-s_nz.jpg',
	]);
	scene.background = texture;


	// Geometry: walls
	var cube = new t.CubeGeometry(UNITSIZE, WALLHEIGHT, UNITSIZE);
	var materials = [
		new t.MeshLambertMaterial({ map: t.ImageUtils.loadTexture('images/wall.jpg') }),

		//  new t.MeshLambertMaterial({color:  0xFBEBCD}),
	];
	for (var i = 0; i < mapW; i++) {
		for (var j = 0, m = map[i].length; j < m; j++) {
			if (map[i][j]) {
				var wall = new t.Mesh(cube, materials[map[i][j] - 1]);
				wall.position.x = (i - units / 2) * UNITSIZE;
				wall.position.y = WALLHEIGHT / 4;
				wall.position.z = (j - units / 2) * UNITSIZE;
				scene.add(wall);
			}
		}
	}

	// Health cube
	healthcube = new t.Mesh(
		new t.CubeGeometry(30, 30, 30),
		new t.MeshBasicMaterial({ map: t.ImageUtils.loadTexture('images/health.png') })
	);
	healthcube.position.set(-UNITSIZE - 15, 35, -UNITSIZE - 15);
	scene.add(healthcube);

	//model load
	for (var _key in models) {
		(function (key) {

			var mtlLoader = new t.MTLLoader(loadingManager);
			mtlLoader.load(models[key].mtl, function (materials) {
				materials.preload();

				var objLoader = new t.OBJLoader(loadingManager);

				objLoader.setMaterials(materials);
				objLoader.load(models[key].obj, function (mesh) {

					mesh.traverse(function (node) {
						if (node instanceof t.Mesh) {
							if ('castShadow' in models[key])
								node.castShadow = models[key].castShadow;
							else
								node.castShadow = true;

							if ('receiveShadow' in models[key])
								node.receiveShadow = models[key].receiveShadow;
							else
								node.receiveShadow = true;
						}
					});
					models[key].mesh = mesh;

				});
			});

		})(_key);
	}


	// Lighting
	var directionalLight1 = new t.DirectionalLight(0xffffff, 1);
	directionalLight1.position.set(0.5, 1, 0.5);
	scene.add(directionalLight1);
	var directionalLight2 = new t.DirectionalLight(0xffffff, 1);
	directionalLight2.position.set(-0.5, -1, -0.5);
	scene.add(directionalLight2);
}

var enemmy = [];
var enemmyGeo = new t.CubeGeometry(40, 40, 40);
function setupENEMMY() {
	for (var i = 0; i < numenemmies; i++) {
		addesetupENEMMY();
	}
}

function addesetupENEMMY() {
	var c = getMapSector(cam.position);
	var enemmyMaterial = new t.MeshBasicMaterial({/*color: 0xEE3333,*/map: t.ImageUtils.loadTexture('images/mask.png') });
	var o = new t.Mesh(enemmyGeo, enemmyMaterial);
	do {
		var x = getRandBetween(0, mapW - 1);
		var z = getRandBetween(0, mapH - 1);
	} while (map[x][z] > 0 || (x == c.x && z == c.z));
	x = Math.floor(x - mapW / 2) * UNITSIZE;
	z = Math.floor(z - mapW / 2) * UNITSIZE;
	o.position.set(x, UNITSIZE * 0.15, z);
	o.health = 100;
	//o.path = getesetupENEMMYpath(o);
	o.pathPos = 1;
	o.lastRandomX = Math.random();
	o.lastRandomZ = Math.random();
	o.lastShot = Date.now(); // Higher-fidelity timers aren't a big deal here.
	enemmy.push(o);
	scene.add(o);
}

function getesetupENEMMYpath(a) {
	var p = getMapSector(a.position);
	do { // Cop-out
		do {
			var x = getRandBetween(0, mapW - 1);
			var z = getRandBetween(0, mapH - 1);
		} while (map[x][z] > 0 || distance(p.x, p.z, x, z) < 3);
		var path = findesetupENEMMYpath(p.x, p.z, x, z);
	} while (path.length == 0);
	return path;
}

/**
 * Find a path from one grid cell to another.
 *
 * @param sX
 *   Starting grid x-coordinate.
 * @param sZ
 *   Starting grid z-coordinate.
 * @param eX
 *   Ending grid x-coordinate.
 * @param eZ
 *   Ending grid z-coordinate.
 * @returns
 *   An array of coordinates including the start and end positions representing
 *   the path from the starting cell to the ending cell.
 */
function findesetupENEMMYpath(sX, sZ, eX, eZ) {
	var backupGrid = grid.clone();
	var path = finder.findPath(sX, sZ, eX, eZ, grid);
	grid = backupGrid;
	return path;
}

function distance(x1, y1, x2, y2) {
	return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
}
//이함수 왜이러냐 원래 var x = Math.floor((v.x + UNITSIZE /2) / UNITSIZE + mapW/2);
// var z = Math.floor((v.z + UNITSIZE /2) / UNITSIZE + mapW/2);

function getMapSector(v) {
	var x = Math.floor((v.x + UNITSIZE) / UNITSIZE + mapW / 2);
	var z = Math.floor((v.z + UNITSIZE) / UNITSIZE + mapW / 2);
	return { x: x, z: z };
}

/**
 * Check whether a Vector3 overlaps with a wall.
 *
 * @param v
 *   A THREE.Vector3 object representing a point in space.
 *   Passing cam.position is especially useful.
 * @returns {Boolean}
 *   true if the vector is inside a wall; false otherwise.
 */
function checkWallCollision(v) {
	var c = getMapSector(v);
	return map[c.x][c.z] > 0;
}

// Radar
function drawRadar() {
	var c = getMapSector(cam.position), context = document.getElementById('radar').getContext('2d');
	context.font = '10px Helvetica';
	for (var i = 0; i < mapW; i++) {
		for (var j = 0, m = map[i].length; j < m; j++) {
			var d = 0;
			for (var k = 0, n = enemmy.length; k < n; k++) {
				var e = getMapSector(enemmy[k].position);
				if (i == e.x && j == e.z) {
					d++;
				}
			}
			if (i == c.x && j == c.z && d == 0) {
				context.fillStyle = '#0000FF';
				context.fillRect(i * 20, j * 20, (i + 1) * 20, (j + 1) * 20);
			}
			else if (i == c.x && j == c.z) {
				context.fillStyle = '#AA33FF';
				context.fillRect(i * 20, j * 20, (i + 1) * 20, (j + 1) * 20);
				context.fillStyle = '#000000';
				context.fillText('' + d, i * 20 + 8, j * 20 + 12);
			}
			else if (d > 0 && d < 10) {
				context.fillStyle = '#FF0000';
				context.fillRect(i * 20, j * 20, (i + 1) * 20, (j + 1) * 20);
				context.fillStyle = '#000000';
				context.fillText('' + d, i * 20 + 8, j * 20 + 12);
			}
			else if (map[i][j] > 0) {
				context.fillStyle = '#666666';
				context.fillRect(i * 20, j * 20, (i + 1) * 20, (j + 1) * 20);
			}
			else {
				context.fillStyle = '#CCCCCC';
				context.fillRect(i * 20, j * 20, (i + 1) * 20, (j + 1) * 20);
			}
		}
	}
}
var bulletType=[]
var bullets = [];
var sphereMaterial = new t.MeshBasicMaterial({ color: 0x333333 });
var sphereGeo = new t.SphereGeometry(2, 6, 6);
function createBullet(obj) {
	if (obj === undefined) {
		obj = cam;
	}
	var sphere = new t.Mesh(sphereGeo, sphereMaterial);
	sphere.position.set(obj.position.x, obj.position.y * 0.8, obj.position.z);

	if (obj instanceof t.Camera) {
		var vector = new t.Vector3(mouse.x, mouse.y, 1);
		projector.unprojectVector(vector, obj);
		sphere.ray = new t.Ray(
			obj.position,
			vector.sub(obj.position).normalize()
		);
	}
	else {
		var vector = cam.position.clone();
		sphere.ray = new t.Ray(
			obj.position,
			vector.sub(obj.position).normalize()
		);
	}
	sphere.owner = obj;

	bullets.push(sphere);
	scene.add(sphere);

	return sphere;
}

function onDocumentMouseMove(e) {
	e.preventDefault();
	mouse.x = (e.clientX / WIDTH) * 2 - 1;
	mouse.y = - (e.clientY / HEIGHT) * 2 + 1;
}

// Handle window resizing
$(window).resize(function () {
	WIDTH = window.innerWidth;
	HEIGHT = window.innerHeight;
	ASPECT = WIDTH / HEIGHT;
	if (cam) {
		cam.aspect = ASPECT;
		cam.updateProjectionMatrix();
	}
	if (renderer) {
		renderer.setSize(WIDTH, HEIGHT);
	}
	$('#intro, #hurt').css({ width: WIDTH, height: HEIGHT, });
});

// Stop moving around when the window is unfocused (keeps my sanity!)
$(window).focus(function () {
	if (controls) controls.freeze = false;
});
$(window).blur(function () {
	if (controls) controls.freeze = true;
});

//Get a random integer between lo and hi, inclusive.
//Assumes lo and hi are integers and lo is lower than hi.
function getRandBetween(lo, hi) {
	return parseInt(Math.floor(Math.random() * (hi - lo + 1)) + lo, 10);
}



