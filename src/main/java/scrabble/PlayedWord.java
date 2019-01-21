package scrabble;

class PlayedWord{
    @Override
    public String toString() {
        return "PlayedWord [word=" + word + ", score=" + score + "]";
    }
    public PlayedWord(String word, int score) {
        this.word = word;
        this.score = score;
    }


    public boolean equals(PlayedWord other) {
        if (other.word == this.word){
            return true;
        }
        return false;

    }
    String word;
    int score;
}
