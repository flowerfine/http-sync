package cn.sliew.http.stream.web.controller.remote;

import cn.sliew.http.stream.remote.github.GithubRemoteService;
import cn.sliew.http.stream.remote.github.client.commit.CommitListDO;
import cn.sliew.http.stream.remote.github.client.commit.CommitListQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/remote/github")
@Tag(name = "外部接口-github", description = "github 接口")
public class GithubController {

    @Autowired
    private GithubRemoteService githubRemoteService;

    @GetMapping("{owner}/{repo}/commits")
    @Operation(description = "查询仓库提交记录")
    public List<CommitListDO> listCommits(@PathVariable("owner") String owner,
                                          @PathVariable("repo") String repo,
                                          CommitListQuery query) {
        return githubRemoteService.listCommits(owner, repo, query);
    }
}
