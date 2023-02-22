import java.util.*;
import java.time.*;

public class AdyenSolution {

    static class Payment {
        private final String paymentId;
        private final Instant timestamp;
        private final String hashedCardNumber;

        public Payment(String paymentId, Instant timestamp, String hashedCardNumber) {
            this.paymentId = paymentId;
            this.timestamp = timestamp;
            this.hashedCardNumber = hashedCardNumber;
        }

        public String getPaymentId() {
            return paymentId;
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public String getHashedCardNumber() {
            return hashedCardNumber;
        }
    }


    interface VelocityProvider {

        /**
         * This method is called during the payment risk assessment.
         * <p>
         * It returns how many times the card in the Payment has been seen in the last minutes/seconds/hours as
         * defined in the {@code duration} parameter at the time the payment is being processed.
         *
         * @param payment  The payment being processed
         * @param duration The interval to count
         * @return The number of times the card was used in the interval defined in duration.
         */
        int getCardUsageCount(Payment payment, Duration duration);


        /**
         * After the payment is processed this method is called.
         *
         * @param payment The payment that has been processed.
         */
        void registerPayment(Payment payment);

        /**
         * @return Instance of a Velocity provider
         */
        static VelocityProvider getProvider() {
            return new VelocityProvider() {
                private final Map<String, List<Instant>> cardPayments = new HashMap<>();

                @Override
                public int getCardUsageCount(Payment payment, Duration duration) {
                    String cardNumber = payment.getHashedCardNumber();
                    Instant queryTime = payment.getTimestamp();

                    if (cardPayments.containsKey(cardNumber)) {
                        List<Instant> paymentTimes = cardPayments.get(cardNumber);
                        Instant startTime = queryTime.minus(duration);
                        return (int) paymentTimes.stream()
                                .filter(paymentTime -> (paymentTime.isAfter(startTime) || paymentTime.compareTo(startTime) == 0) &&
                                        paymentTime.isBefore(queryTime))
                                .count();
                    } else {
                        return 0;
                    }
                }

                @Override
                public void registerPayment(Payment payment) {
                    String cardNumber = payment.getHashedCardNumber();
                    Instant paymentTime = payment.getTimestamp();

                    if (cardPayments.containsKey(cardNumber)) {
                        List<Instant> paymentTimes = cardPayments.get(cardNumber);
                        paymentTimes.add(paymentTime);
                    } else {
                        List<Instant> paymentTimes = new ArrayList<>();
                        paymentTimes.add(paymentTime);
                        cardPayments.put(cardNumber, paymentTimes);
                    }
                }
            };
        }

    }


    public static void main(String args[]) throws Exception {
        final VelocityProvider velocityProvider = VelocityProvider.getProvider();

        try (final Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                final String assoc = scanner.next();
                final String[] split = assoc.split(":");

                final String operation = split[0];

                if (split.length == 3 && "register".equals(operation)) {
                    final long timestamp = Long.parseLong(split[1]);
                    final String hashedCardNumber = split[2];
                    final Payment payment = new Payment(UUID.randomUUID().toString(), Instant.ofEpochMilli(timestamp), hashedCardNumber);

                    velocityProvider.registerPayment(payment);
                } else if (split.length == 4 && "get".equals(operation)) {
                    final long queryTime = Long.parseLong(split[1]);
                    final String hashedCardNumber = split[2];
                    final long durationInSeconds = Long.parseLong(split[3]);
                    System.out.println(velocityProvider.getCardUsageCount(new Payment(UUID.randomUUID().toString(), Instant.ofEpochMilli(queryTime), hashedCardNumber), Duration.ofSeconds(durationInSeconds)));
                } else {
                    throw new RuntimeException("Invalid test input");
                }
            }
        }
    }
}
