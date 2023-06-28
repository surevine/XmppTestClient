# XmppTestClient

A Smack-based XMPP client to run ad-hoc XMPP tests against an XMPP server such as Openfire.

## Running the application

1. Modify the TestClient class attribute values to configure your server. The default values are for an insecure connection to a locally hosted XMPP server at example.org (i.e. Openfire's demoboot configuration).

2. Run the TestClient main function from IDE or CLI; this will open the Smack client pane. From here you can look at the passing of XMPP stanza's between the client and server, as well as sending messages from the client. 

*Note: Messages must be formatted as XMPP stanzas, for example:*

```
<message to="john@example.org" from="jane@example.org">
  <body>Hi John!</body>
</message>
```
