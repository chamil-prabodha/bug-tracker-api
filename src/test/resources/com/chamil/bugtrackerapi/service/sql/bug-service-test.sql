INSERT INTO project(`id`, `name`, `project_key`) values (1, 'Project 1', 'project-1');
INSERT INTO bug(`id`, `name`, `reported_by`, `status`, `severity`, `project_id`) values (1, 'Bug 1', 'USER 1', 'OPEN', 'LOW', 1);
INSERT INTO bug(`id`, `name`, `reported_by`, `status`, `severity`, `project_id`) values (2, 'Bug 2', 'USER 2', 'CLOSED', 'MEDIUM', 1);
