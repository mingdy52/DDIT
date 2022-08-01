var arr45 = [
	1,2,3,4,5,		6,7,8,9,10,
	11,12,13,14,15,	16,17,18,19,20,
	21,22,23,24,25,	26,27,28,29,30,
	31,32,33,34,35,	36,37,38,39,40,
	41,42,43,44,45
];


for(var i=0;i<100;i++){
	var rnd = parseInt(Math.random()*45);
	var a = arr45[0];
	var b = arr45[rnd];
	arr45[0] = b;
	arr45[rnd] = a;
}

console.log(arr45[0],arr45[1],arr45[2],arr45[3],arr45[4],arr45[5]);
