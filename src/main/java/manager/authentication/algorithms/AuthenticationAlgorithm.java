package manager.authentication.algorithms;

import com.auth0.jwt.algorithms.Algorithm;

public interface AuthenticationAlgorithm {
    Algorithm getAlgorithm();
}
