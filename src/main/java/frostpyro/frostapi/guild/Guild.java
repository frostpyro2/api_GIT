package frostpyro.frostapi.guild;

import frostpyro.frostapi.dataManage.stat.StatProvider;

import java.util.List;
import java.util.UUID;

public class Guild {
    private StatProvider guildMaster;
    private List<StatProvider> guildMember;
    private String masterName;
    private UUID masterUUID;

    public Guild(){

    }

    public Guild(StatProvider guildMaster, List<StatProvider> guildMember){
        this.guildMaster = guildMaster;
        this.guildMember = guildMember;
        masterName = guildMaster.getName();
        masterUUID = guildMaster.getUuid();
    }

    public StatProvider getGuildMaster() {
        return guildMaster;
    }

    public void setGuildMaster(StatProvider guildMaster) {
        this.guildMaster = guildMaster;
    }

    public List<StatProvider> getGuildMember() {
        return guildMember;
    }

    public void setGuildMember(List<StatProvider> guildMember) {
        this.guildMember = guildMember;
    }

    public void deleteGuildMember(StatProvider deleteMember){
        guildMember.remove(deleteMember);
    }

    public void addGuildMember(StatProvider newGuildMember) {
        guildMember.add(newGuildMember);
    }

    public boolean isGuildMember(StatProvider member){
        return guildMember.contains(member);
    }

    @Deprecated
    public static Guild get(StatProvider master){
        return null;
    }
}
