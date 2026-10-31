# Releasing

Releases are published to Maven Central by the [Publish](.github/workflows/publish.yml) workflow when a `v*` tag is
pushed. It runs on macOS, because Apple targets can only be published from there.

## One-time setup

1. Sign in at [central.sonatype.com](https://central.sonatype.com) and add the `com.theakram` namespace. Verify it
   by adding the DNS TXT record it shows you to `theakram.com`.
2. Under **View Account → Generate User Token**, create a token.
3. Create a GPG key for signing and publish the public key to a key server:

   ```shell
   gpg --full-generate-key
   gpg --keyserver keyserver.ubuntu.com --send-keys <KEY_ID>
   ```

4. Add these repository secrets on GitHub (**Settings → Secrets and variables → Actions**):

   | Secret                   | Value                                                  |
   |--------------------------|--------------------------------------------------------|
   | `MAVEN_CENTRAL_USERNAME` | Token username                                         |
   | `MAVEN_CENTRAL_PASSWORD` | Token password                                         |
   | `SIGNING_KEY`            | Output of `gpg --export-secret-keys --armor <KEY_ID>` |
   | `SIGNING_KEY_PASSWORD`   | The key's passphrase                                   |

## Publishing a release

1. Set `version` in [gradle.properties](gradle.properties) and move the `Unreleased` entries in
   [CHANGELOG.md](CHANGELOG.md) under the new version. Commit both.
2. Tag the commit and push the tag:

   ```shell
   git tag v0.1.0
   git push origin v0.1.0
   ```

The workflow checks the tag matches `version`, runs the tests and publishes. Artifacts show up on Maven Central
within about half an hour.

To check what would be published without uploading anything, run `./gradlew publishToMavenLocal` and look in
`~/.m2/repository/com/theakram/`. Artifacts are signed only when a signing key is configured.
