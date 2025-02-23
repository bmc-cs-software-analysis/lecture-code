public class RankSelection {

    /**
     * Returns the index of the next individual selected from the given
     * population, which is assumed to be already sorted.
     *
     * @param population the population to select an individual from
     * @return the index of the selected individual in the population
     * @implNote Approximates the index of the selected individual in {@code
     * O(1)} by transforming an equally distributed random variable {@code
     * 0 <= r <= 1}, as described by Whitley in the GENITOR algorithm (1989).
     * For rank biases between 1 and 2, this produces results almost identical
     * to the text-book specification of rank selection.
     */
    @Override
    public static int getIndex(List<T> population) {
        double r = Randomness.nextDouble();
        double d = Properties.RANK_BIAS
            - Math.sqrt((Properties.RANK_BIAS * Properties.RANK_BIAS)
                    - (4.0 * (Properties.RANK_BIAS - 1.0) * r));
        int length = population.size();

        d = d / 2.0 / (Properties.RANK_BIAS - 1.0);

        //this is not needed because population is sorted based on Maximization
        //if(maximize)
        //	d = 1.0 - d; // to do that if we want to have Maximisation

        int index = (int) (length * d);
        return index;
    }

}
