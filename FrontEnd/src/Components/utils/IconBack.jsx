import { useLocation,useNavigate } from "react-router-dom";
import { IoIosArrowBack } from "react-icons/io";

const IconBack = () => {
    const navigate = useNavigate();
    const location = useLocation();
    if (location.pathname === "/") {
        return null;
     }
    return  <IoIosArrowBack color="white" size={35} onClick={()=> navigate(-1)}/>
    
}

export default IconBack