class Animal{
   constructor(){
      this.age=1;
   }
   getOld(){
      this.age++;
   }

}
export default Animal;


var a = new Animal();
console.log("a.age",a.age);
a.getOld();
console.log("a.age",a.age);