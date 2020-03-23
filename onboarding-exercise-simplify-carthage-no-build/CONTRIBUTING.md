# Welcome

Welcome to the Itty Bitty Apps onboarding exercise!

## How to get started

Start by reading the [README.md](README.md).

### Ensure your Git client is correctly configured

Please ensure you have set your email address and name correctly in your Git config. You can do this like so:

```sh
git config user.name "Joe Contributor"
git config user.email "jcontrib@example.com"
```

☝️ Remember to replace the name and email address above with your actual name and email address. You should use an email address associated with your GitHub account. It doesn't need to necessarily be your `ittybittyapps.com` email address.

### Ensure your GitHub account is correctly configured

Fill out all the details of your [Public Profile on GitHub](https://github.com/settings/profile) including a profile picture.

## How to submit changes

At Itty Bitty Apps we use the standard [GitHub flow](https://guides.github.com/introduction/flow/). But for authorized IBA project contributors it is not usally necessary to fork the repository before pushing a branch, however the `onboarding-exercise` project is slightly different in this regard as it does require forking.

### Fork the repository

Use the "Fork" button on GitHub to create your own copy of this repository.

### Clone your fork of the repository

Once you've forked the repo you'll need to download (clone) it to your computer.

```sh
git clone git@github.com:<your-github-username>/onboarding-exercise
```

☝️ Don't forget to replace `<your-github-username>` with your actual Github username.

### Create a branch

```sh
cd onboarding-exercise
git checkout -b feature/1234-short-feature-description
```

Branch names should include a prefix such as `tech`, `feature`, `doc`, or `fix` for technical tasks, feature development, documentation, or bugfixes respectively. It is also a good idea to include the GitHub issue number the branch relates to in the branch name before the human readable description of the branch.

### Commit code, Unit Tests, and UI Tests

```sh
git add MyFeature.swift
git commit
```

- Do your work in the branch you created.
- When working with others, rebase or merge your branch with `origin/master` on a regular basis while you are working on your branch to keep it up to date with `master`.
  - Rebasing your work on `origin/master` can be done with the following command: `git fetch && git rebase origin/master`
- Commit regularly. Commit small chunks of work.
- Try and ensure each commit compiles without uncommited work.
- Provide commit messages that describe **why** you're changing files not **what** you changed.
- Keep your total changes as small as you can. Don't do too much work in a single branch is it makes it harder to code review.
- Write unit (and optionally UI automation) tests for your feature or bugfix.

#### Commit messages

First read Chris Beams' post on [How to Write a Git Commit Message](https://chris.beams.io/posts/git-commit/).

#### The seven rules of a great Git commit message

> Keep in mind: [This](https://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html) [has](https://www.git-scm.com/book/en/v2/Distributed-Git-Contributing-to-a-Project#_commit_guidelines) [all](https://github.com/torvalds/subsurface-for-dirk/blob/master/README#L92-L120) [been](http://who-t.blogspot.com/2009/12/on-commit-messages.html) [said](https://github.com/erlang/otp/wiki/writing-good-commit-messages) [before](https://github.com/spring-projects/spring-framework/blob/30bce7/CONTRIBUTING.md#format-commit-messages).

1. Separate subject from body with a blank line
2. Limit the subject line to 50 characters
3. Capitalize the subject line
4. Do not end the subject line with a period
5. Use the imperative mood in the subject line
6. Wrap the body at 72 characters
7. Use the body to explain what and why, not how

### Test your contribution

Run the project unit tests and correct any issues by the tests found before pushing your branch.

#### Push your branch and create a Pull Request

- Push your branch, eg: `git push origin [your branch name]`
- Open a Pull Request - following the [Pull Request Template](.github/PULL_REQUEST_TEMPLATE.md).
- Invite your mentor or someone from the IBA engineering team to review your code before merging it to `master`.
