package com.example.base.exceptions

class EntityNotFoundException extends BaseException{

    EntityNotFoundException(){
        super()
    }

    EntityNotFoundException(String message){
        super(message)
    }
}
