package project;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.eclipse.egit.github.core.Authorization;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.RequestException;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.WatcherService;
public class SocialGraph {
	public static void main(String[] args) {
	    Scanner scanner = new Scanner( System.in );
	    System.out.print( "Enter Github username: \n" );
	    String input = scanner.nextLine();
	    System.out.print( "Enter Github password: \n" );
	    String input2 = scanner.nextLine();
	    
		String username = input;
		String password = input2;
		
		try {
			GitHubClient client = new GitHubClient();
			client.setCredentials(username, password);
			RepositoryService repServ = new RepositoryService(client);
			List<Repository> repList = repServ.getRepositories();
			for(int i =0;i<repList.size();i++) {
				System.out.print(username+"'s repository, "+repList.get(i).getName()+", URL"+repList.get(i).getGitUrl()+"\n");
			}
			WatcherService waServ = new WatcherService();
			try {
				repList = waServ.getWatched(username);
				for(int i =0;i<repList.size();i++) {
					System.out.print(username+"'s watched repository, "+repList.get(i).getName()+", URL"+repList.get(i).getGitUrl()+"\n");
				}
			}catch(RequestException e) {
				System.out.print("No watched repos found.");
			}
		}catch(IOException e) {
			System.out.print("May have been errors in Credentials\n");
		}
		
	}
}
