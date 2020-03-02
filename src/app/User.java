package app;

class User{
    String username;
    String email;
    String passwordHashed;
    byte[] salt;
    private String role;

    public String getUserRole(){
        return this.role;
    }
    public void seUserRole(String role){
        this.role = role;
    }
}
