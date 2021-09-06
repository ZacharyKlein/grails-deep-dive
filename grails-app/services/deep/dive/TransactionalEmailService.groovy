package deep.dive

interface TransactionalEmailService {
    void send(String recipient, String htmlEmail)
}
