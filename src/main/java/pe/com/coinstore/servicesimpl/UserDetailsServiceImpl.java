package pe.com.coinstore.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.com.coinstore.entities.User;
import pe.com.coinstore.repositories.UserRespository;
import pe.com.coinstore.security.SecurityUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRespository userRespository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRespository.findByUserName(username);
        if (user!=null){
            return new SecurityUser(user);
        }
        throw  new UsernameNotFoundException("User not found: "+username);
    }


}
