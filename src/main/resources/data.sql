INSERT INTO project(`id`, `name`, `project_key`) values (1, 'Project 1', 'project-1');

INSERT INTO bug(`id`, `name`, `description`, `reported_by`, `status`, `severity`, `project_id`) values (10, 'Bug 1', 'Description 1', 'USER 1', 'OPEN', 'LOW', 1);
INSERT INTO bug(`id`, `name`, `description`, `reported_by`, `status`, `severity`, `project_id`) values (20, 'Bug 2', 'Description 2', 'USER 2', 'CLOSED', 'MEDIUM', 1);

INSERT INTO comment(`id`, `issue_id`, `content`, `author`) VALUES (10, 10, 'Comment 1', 'USER 1');
INSERT INTO comment(`id`, `issue_id`, `content`, `author`) VALUES (11, 10, 'Comment 2', 'USER 2');
INSERT INTO comment(`id`, `issue_id`, `content`, `author`) VALUES (12, 20, 'Comment 1', 'USER 1');
INSERT INTO comment(`id`, `issue_id`, `content`, `author`) VALUES (13, 20, 'Comment 2', 'USER 2');
