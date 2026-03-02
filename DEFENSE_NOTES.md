The project was given as a starter template. 
Heroes and enemies already exist, but their methods are different. 
To solve this, we use the Adapter pattern with one common interface called Combatant.
The Combatant interface and the two adapters were already provided in the template.
I implemented the battle logic in BattleEngine, 
especially the runEncounter() method:Team A attacks Team B in order, then Team B attacks Team A.
When a combatant dies, I remove it from the fight.
The battle ends when one team has no living combatants.
The result is returned as EncounterResult winner, rounds, and battle log.
I also demonstrated the Singleton behavior of BattleEngine in 
Main.java:engineA == engineB prints true.