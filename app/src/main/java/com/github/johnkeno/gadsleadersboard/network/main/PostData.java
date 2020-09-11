package com.github.johnkeno.gadsleadersboard.network.main;

public class PostData {
    String firstName;
    String lastName;
    String email;
    String GitHubLink;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGitHubLink() {
        return GitHubLink;
    }

    public void setGitHubLink(String gitHubLink) {
        GitHubLink = gitHubLink;
    }
}
