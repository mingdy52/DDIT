import Animal from './main4.js';

class Cat extends Animal {
	constructor () {
		super();
		this.ssagaji = false;
	}
	chur(){
		return this.ssagaji = true;
	}
}

var c = new Cat();

console.log(c.age);
console.log(c.ssagaji);
c.getOld();
c.chur();
console.log(c.age);
console.log(c.ssagaji);