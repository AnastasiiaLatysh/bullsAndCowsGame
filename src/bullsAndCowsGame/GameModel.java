package bullsAndCowsGame;


class GameModel {

    private int bulls = 0;
    private int cows = 0;

    boolean isNumberGuessed(int numberToGuess, int userPotentialNumber){
        boolean isNumberGuessed = false;
        int currentAmountOfBulls = 0;
        int currentAmountOfCows = 0;
        String sUserPotentialNumber = Integer.toString(userPotentialNumber);
        String sNumberToGuess = Integer.toString(numberToGuess);

        for(int i = 0; i < 4; i++){
            if(sUserPotentialNumber.charAt(i) == sNumberToGuess.charAt(i))
                currentAmountOfBulls++;
            else if(sNumberToGuess.contains(sUserPotentialNumber.charAt(i)+""))
                currentAmountOfCows++;
        }
        if(currentAmountOfBulls == 4)
            isNumberGuessed = true;

        bulls = currentAmountOfBulls;
        cows = currentAmountOfCows;

        return isNumberGuessed;
    }

    int getCows(){
        return cows;
    }

    int getBulls(){
        return bulls;
    }
}
