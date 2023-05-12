export class Login{
    userName:String;
    password:String;
    area:String;

    constructor(userName:String, password:String, area:String){
        this.userName=userName;
        this.password=password;
        this.area=area;
    }
    
    public getUserName(){
        return this.userName;
    }

    public  setUserName(userName:String){
        this.userName=userName;
    }
    public getPassword(){
        return this.password;
    }

    public  setPassword(password:String){
        this.password=password;
    }
    public getArea(){
        return this.area;
    }

    public  setArea(area:String){
        this.area=area;
    }
    
}