import axios from "axios";

const SYSTEM_USER_API_BASE_URL = 'http://localhost:8080/api/users';

class SystemUserService{
    getUsers() {
        return axios.get(SYSTEM_USER_API_BASE_URL);
    }
}

export default new SystemUserService();