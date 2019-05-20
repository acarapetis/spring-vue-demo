package com.example.carapetis;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

// Sometimes we need to raise a 404.
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException { 
    private static final long serialVersionUID = 5710361311353499930L;
}
