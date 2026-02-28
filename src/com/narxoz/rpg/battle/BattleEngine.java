package com.narxoz.rpg.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class BattleEngine {
    private static BattleEngine instance;
    private Random random = new Random(1L);

    private BattleEngine() {
    }

    public static BattleEngine getInstance() {
        if (instance == null) {
            instance = new BattleEngine();
        }
        return instance;
    }

    public BattleEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public void reset() {
        // TODO: reset any battle state if you add it
    }

    public EncounterResult runEncounter(List<Combatant> teamA, List<Combatant> teamB) {
        // TODO: validate inputs and run round-based battle
        if (teamA == null || teamB == null){
            throw new IllegalArgumentException("Teams must not be null");
        }
        List<Combatant> a = new ArrayList<>(teamA);
        List<Combatant> b = new ArrayList<>(teamB);

        a.removeIf(c -> c==null || !c.isAlive());
        b.removeIf(c -> c==null || !c.isAlive());

        EncounterResult result = new EncounterResult();
        result.setRounds(0);
        if (a.isEmpty() && b.isEmpty()){
            result.setWinner("Draw");
            result.addLog("both teams are empty so DRAW.");
            return result;
        }
        if (a.isEmpty()) {
            result.setWinner("Team B");
            result.addLog("team A has no living combatants.Team B wins.");
            return result;
        }
        if (b.isEmpty()) {
            result.setWinner("Team A");
            result.addLog("team B has no living combatants.Team A wins.");
            return result;
        }

        int rounds = 0;

        while (!a.isEmpty() && !b.isEmpty()) {
            rounds++;
            result.addLog("---- Round " + rounds + " ----");

            for (int i = 0; i < a.size(); i++) {
                Combatant attacker = a.get(i);
                if (!attacker.isAlive()) continue;
                if (b.isEmpty()) break;

                Combatant target = b.get(0);
                int damage =attacker.getAttackPower();
                if (damage < 0) damage = 0;

                target.takeDamage(damage);
                result.addLog(attacker.getName()+"hits " +target.getName() +"for " +damage);

                if (!target.isAlive()) {
                    result.addLog(target.getName() + "is defeated!");
                    b.remove(0);
                }
            }

            if (b.isEmpty()) break;

            for (int i = 0; i < b.size(); i++) {
                Combatant attacker = b.get(i);
                if (!attacker.isAlive()) continue;
                if (a.isEmpty()) break;

                Combatant target = a.get(0);
                int damage = attacker.getAttackPower();
                if (damage < 0) damage =0;

                target.takeDamage(damage);
                result.addLog(attacker.getName() + "hits " + target.getName() + "for " + damage);

                if (!target.isAlive()) {
                    result.addLog(target.getName() + "is defeated!");
                    a.remove(0);
                }
            }
        }
        result.setRounds(rounds);

        if (a.isEmpty() && b.isEmpty()) {
            result.setWinner("Draw");
        } else if (b.isEmpty()) {
            result.setWinner("Team A");
        } else {
            result.setWinner("Team B");
        }
        return result;


        // TODO: use random if you add critical hits or target selection
//        EncounterResult result = new EncounterResult();
//        result.setWinner("TBD");
//        result.setRounds(0);
//        result.addLog("TODO: implement battle simulation");
//        return result;
    }
}
