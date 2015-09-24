echo "##################"
echo "#Tests SourceFixe#"
echo "##################"

java Simulateur -mess 1010101010101010
java Simulateur -mess 1100110011001100
java Simulateur -mess 1111000011110000
java Simulateur -mess 0110011001100110
java Simulateur -mess 0000111100001111

echo "#######################"
echo "#Tests SourceAleatoire#"
echo "#######################"

java Simulateur -mess 4 -seed 1
java Simulateur -mess 8 -seed 2
java Simulateur -mess 16 -seed 2
java Simulateur -mess 16 -seed 2
java Simulateur -mess 16 -seed 3

