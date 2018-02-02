package ru.stqa.pft.githab;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHabTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("694e8f14c6a7ad9d6dd68b45a5a5942cb5ea8d60");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("NepVik","java_pft_nep")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
