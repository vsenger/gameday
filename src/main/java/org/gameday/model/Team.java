package org.gameday.model;

import java.util.Map;
import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@RegisterForReflection
public class Team {
    //add a new field password with getter and setter
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String password;



    public String teamID;
    public String description;


    public Team() {}

    public static Team from(Map<String, AttributeValue> item) {
        Team team = new Team();
        if (item != null && !item.isEmpty()) {
            team.setTeamID(item.get("teamID").s());
            team.setDescription(item.get("description").s());

        }
        return team;
    }

    public String getTeamID() {
        return this.teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Team)) {
            return false;
        }

        Team other = (Team) obj;

        return Objects.equals(other.teamID, this.teamID)
                && Objects.equals(other.description, this.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.teamID);
    }

}