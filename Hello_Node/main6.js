import Animal from './main4.js';


class Cat extends Animal {
    constructor() {
		super();
        this.ssagaji = false;
    }
    
    onepunch() {
        this.ssagaji = true;
    }
}

var c = new Cat();

console.log(c.age);
console.log(c.ssagaji);
c.getOld();
c.onepunch();
console.log(c.age);
console.log(c.ssagaji);
