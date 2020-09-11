    for(int i = 0; i < str.length() ; i++) {
        stack.push(str.charAt(i));
    }

    for(int j = 0; j < stack.size ; j++){
        stack.pop();
    }


}