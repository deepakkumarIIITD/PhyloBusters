import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GlobalAlignment {
    static class Alignment implements Cloneable {
        private int matches;
        private int misMatches;
        private int gaps;
        private long score;
        private ArrayList <Character> alignmentSequenceOne;
        private ArrayList <Character> alignmentSequenceTwo;

        public Alignment() {
            this.matches = 0;
            this.misMatches = 0;
            this.gaps = 0;
            this.score = 0;
            this.alignmentSequenceOne = new ArrayList <Character> ();
            this.alignmentSequenceTwo = new ArrayList <Character> ();
        }

        @Override
        public Alignment clone() throws CloneNotSupportedException {
            Alignment alignment = (Alignment) super.clone();
            alignment.alignmentSequenceOne = new ArrayList <> (this.alignmentSequenceOne);
            alignment.alignmentSequenceTwo = new ArrayList <> (this.alignmentSequenceTwo);

            return alignment;
        }

        String getAlignmentSequenceOne() {
            StringBuilder builder = new StringBuilder(this.alignmentSequenceOne.size());

            for (int i = this.alignmentSequenceOne.size() - 1 ; i >= 0 ; i--)  {
                builder.append(this.alignmentSequenceOne.get(i));
            }

            return builder.toString();
        }

        String getAlignmentSequenceTwo() {
            StringBuilder builder = new StringBuilder(this.alignmentSequenceTwo.size());

            for (int i = this.alignmentSequenceTwo.size() - 1 ; i >= 0 ; i--)  {
                builder.append(this.alignmentSequenceTwo.get(i));
            }

            return builder.toString();
        }

        String getAlignment() {
            String output = "";
            StringBuilder builder = new StringBuilder(this.alignmentSequenceOne.size());

            for (Character character : this.alignmentSequenceOne)  {
                builder.append(character);
            }

            output += builder.toString();
            output += "\n";
            builder = new StringBuilder(this.alignmentSequenceTwo.size());

            for (Character character : this.alignmentSequenceTwo)  {
                builder.append(character);
            }

            output += builder.toString();

            return output;
        }

        void addAlignmentSequenceOneElement(char element) {
            this.alignmentSequenceOne.add(element);
        }

        void addAlignmentSequenceTwoElement(char element) {
            this.alignmentSequenceTwo.add(element);
        }

        void addToScore(long value) {
            this.score += value;
        }

        void updateScore(long score) {
            this.score = score;
        }

        void resetScore() {
            this.score = 0;
        }

        long getScore() {
            return this.score;
        }

        void incrementMatches() {
            this.matches += 1;
        }

        void incrementMisMatches() {
            this.misMatches += 1;
        }

        void decrementGaps() {
            this.gaps -= 1;
        }

        void decrementMatches() {
            this.matches -= 1;
        }

        void decrementMisMatches() {
            this.misMatches -= 1;
        }

        void incrementGaps() {
            this.gaps += 1;
        }

        int getMatches() {
            return this.matches;
        }

        int getGaps() {
            return this.gaps;
        }

        int getMisMatches() {
            return this.misMatches;
        }
    }

    static class Key {
        private final int x;
        private final int y;

        public Key(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return Integer.toString(this.x) + " " + Integer.toString(this.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)  {
                return true;
            }

            if (!(o instanceof Key)) {
                return false;
            }

            Key key = (Key) o;

            return this.x == key.x && this.y == key.y;
        }

        @Override
        public int hashCode() {
            return this.x * 31 + this.y;
        }
    }

    private static void findGlobalAlignments(String sequenceOne, String sequenceTwo, Key key, HashMap <Key, ArrayList <Key>> links, Alignment alignment, int matchScore, int misMatchScore, int gapScore) {
        try {
            ArrayList <Key> parents = links.get(key);

            for (Key parentKey : parents) {
                if (parentKey.x == key.x - 1 && parentKey.y == key.y - 1) {
                    if (sequenceOne.charAt(key.x - 1) == sequenceTwo.charAt(key.y - 1)) {
                        alignment.addAlignmentSequenceOneElement(sequenceOne.charAt(key.x - 1));
                        alignment.addAlignmentSequenceTwoElement(sequenceTwo.charAt(key.y - 1));
                        alignment.incrementMatches();
                    } else {
                        alignment.addAlignmentSequenceOneElement(sequenceOne.charAt(key.x - 1));
                        alignment.addAlignmentSequenceTwoElement(sequenceTwo.charAt(key.y - 1));
                        alignment.incrementMisMatches();
                    }
                } else if (parentKey.x == key.x - 1 && parentKey.y == key.y) {
                    alignment.addAlignmentSequenceOneElement(sequenceOne.charAt(key.x - 1));
                    alignment.addAlignmentSequenceTwoElement('-');
                    alignment.incrementGaps();
                } else if (parentKey.x == key.x && parentKey.y == key.y - 1) {
                    alignment.addAlignmentSequenceOneElement('-');
                    alignment.addAlignmentSequenceTwoElement(sequenceTwo.charAt(key.y - 1));
                    alignment.incrementGaps();
                }

                findGlobalAlignments(sequenceOne, sequenceTwo, parentKey, links, alignment.clone(), matchScore, misMatchScore, gapScore);
            }
        } catch (Exception e) {
            alignment.updateScore(alignment.getMatches() * matchScore + alignment.getMisMatches() * misMatchScore + alignment.getGaps() * gapScore);
            alignments.add(alignment);
        }
    }

    public static void printLinks(HashMap <Key, ArrayList <Key>> links) {
        for (Map.Entry<Key, ArrayList <Key>> entry : links.entrySet()) {
            System.out.println("Key = " + entry.getKey().toString() +
                    ", Value = " + entry.getValue().size());
        }
    }

    static ArrayList <Alignment> alignments;

    public static long [][] globalAlignment(String sequenceOne, String sequenceTwo, int matchScore, int misMatchScore, int gapScore) {
        int sequenceOneLength = sequenceOne.length();
        int sequenceTwoLength = sequenceTwo.length();
        HashMap <Key, ArrayList <Key>> links = new HashMap <Key, ArrayList <Key>> ();
        alignments = new ArrayList <Alignment> ();
        long [][] scoresTable = new long [sequenceOneLength + 1][sequenceTwoLength + 1];

        // Adding initial scores
        scoresTable[0][0] = 0;

        for (int i = 1 ; i <= sequenceOneLength ; i++) {
            scoresTable[i][0] = gapScore * i;
        }

        for (int i = 1 ; i <= sequenceTwoLength ; i++) {
            scoresTable[0][i] = gapScore * i;
        }

        // Scoring matrix
        for (int i = 1 ; i <= sequenceOneLength ; i++) {
            for (int j = 1 ; j <= sequenceTwoLength ; j++) {
                long tempScore = (sequenceOne.charAt(i - 1) == sequenceTwo.charAt(j - 1)) ? scoresTable[i - 1][j - 1]  + matchScore : scoresTable[i - 1][j - 1] + misMatchScore;
                long [] scores = new long [] {scoresTable[i - 1][j] + gapScore, tempScore, scoresTable[i][j - 1] + gapScore};
                long selectedScore = scores[0];

                for (long score : scores) {
                    if (selectedScore < score) {
                        selectedScore = score;
                    }
                }

                scoresTable[i][j] = selectedScore;

                links.put(new Key(i, j), new ArrayList <Key> ());

                if (selectedScore == scores[0]) {
                    links.get(new Key(i, j)).add(new Key(i - 1, j));
                }

                if (selectedScore == scores[1]) {
                    links.get(new Key(i, j)).add(new Key(i - 1, j - 1));
                }

                if (selectedScore == scores[2]) {
                    links.get(new Key(i, j)).add(new Key(i, j - 1));
                }
            }
        }

//        printLinks(links);

        findGlobalAlignments(sequenceOne, sequenceTwo, new Key(sequenceOneLength, sequenceTwoLength), links, new Alignment(), matchScore, misMatchScore, gapScore);

        return scoresTable;
    }

    public static void main(String [] args) {
        String sequenceOne = "HANDWRITTEN";
        String sequenceTwo = "HERBIVORE";
        int matchScore = 1;
        int misMatchScore = -1;
        int gapScore = -1;
        long [][] scoresTable = globalAlignment(sequenceOne, sequenceTwo, matchScore, misMatchScore, gapScore);

        for (Alignment alignment : alignments) {
            System.out.println(alignment.getAlignmentSequenceTwo());
            System.out.println(alignment.getAlignmentSequenceOne());
            System.out.println("Matches --> " + alignment.getMatches());
            System.out.println("Mis Matches --> " + alignment.getMisMatches());
            System.out.println("Gaps --> " + alignment.getGaps());
            System.out.println("Score --> " + alignment.getScore());
            System.out.println();
        }
    }
}