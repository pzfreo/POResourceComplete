package freo.me.rest;

import org.wso2.msf4j.MicroservicesRunner;

public class MSF4JApplication {
    public static void main(String[] args) {
        new MicroservicesRunner()
                .deploy(new POResource())
                .start();
    }
}