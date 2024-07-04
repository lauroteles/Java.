import axios from "axios";



const httpCliet = axios.create({
    baseURL:'http://localhost:8080'


})
class ApiService {

    constructor(apiurl) {
        this.apiurl = apiurl;
    }
    post(url,objeto) {

        const requestUrl = `${this.apiurl}${url}`
        return httpCliet.post(requestUrl,objeto);
    }

    put(url,objeto) {
        const requestUrl = `${this.apiurl}${url}`
        return httpCliet.post(requestUrl,objeto);
    }

    delete(url) {
        const requestUrl = `${this.apiurl}${url}`
        return httpCliet.delete(requestUrl);
    }
    get(url) {
        const requestUrl = `${this.apiurl}${url}`
        return httpCliet.get(requestUrl);
    }    
    
}


export default ApiService;







